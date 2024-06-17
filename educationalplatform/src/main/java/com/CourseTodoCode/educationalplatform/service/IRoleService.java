package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    public List<Role> findAll();
    public Optional<Role> findById(Long id);
    public Role save(Role role);
    public Optional<Role> update(Long id, Role role);
    public void deleteById(Long id);
}
