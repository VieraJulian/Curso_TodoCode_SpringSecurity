package com.CourseTodoCode.educationalplatform.controller;

import com.CourseTodoCode.educationalplatform.model.Role;
import com.CourseTodoCode.educationalplatform.model.UserEntity;
import com.CourseTodoCode.educationalplatform.service.IRoleService;
import com.CourseTodoCode.educationalplatform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        try {
            List<UserEntity> usersList = userService.findAll();
            return new ResponseEntity<>(usersList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        try {
            Optional<UserEntity> user = userService.findById(id);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        try {
            Set<Role> roleList = new HashSet<>();
            Role roleRead;

            for (Role role : user.getRoleList()) {
                roleRead = roleService.findById(role.getId()).orElse(null);

                if (roleRead != null) {
                    roleList.add(roleRead);
                }
            }

            if (!roleList.isEmpty()) {
                user.setRoleList(roleList);
                UserEntity userCreated = userService.save(user);
                return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
            }

            return null;

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
