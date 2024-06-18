package com.CourseTodoCode.educationalplatform.controller;

import com.CourseTodoCode.educationalplatform.model.Permission;
import com.CourseTodoCode.educationalplatform.model.Role;
import com.CourseTodoCode.educationalplatform.service.IPermissionService;
import com.CourseTodoCode.educationalplatform.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roleList = roleService.findAll();
            return new ResponseEntity<>(roleList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        try {
            Optional<Role> role = roleService.findById(id);
            return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Set<Permission> permissionsList = new HashSet<>();
            Permission permissionRead;

            for (Permission permission : role.getPermissionsList()) {
                permissionRead = permissionService.findById(permission.getId()).orElseGet(null);

                if (permissionRead != null) {
                    permissionsList.add(permissionRead);
                }
            }

            if (!permissionsList.isEmpty()) {
                role.setPermissionsList(permissionsList);
                Role roleNew = roleService.save(role);
                return new ResponseEntity<>(roleNew, HttpStatus.CREATED);
            }

            return null;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
