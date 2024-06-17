package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    public List<Permission> findAll();
    public Optional<Permission> findById(Long id);
    public Permission save(Permission permission);
    public Optional<Permission> update(Long id, Permission permission);
    public void deleteById(Long id);
}
