package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.Permission;
import com.CourseTodoCode.educationalplatform.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> update(Long id, Permission permission) {
        Optional<Permission> permissionDB = permissionRepository.findById(id);

        if (permissionDB.isPresent()) {
            Permission permissionUpdated = permissionDB.get();
            permissionUpdated.setPermissionName(permission.getPermissionName());
            return Optional.of(permissionRepository.save(permissionUpdated));

        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
}
