package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.Role;
import com.CourseTodoCode.educationalplatform.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Object> update(Long id, Role role) {
        Optional<Role> roleDB = roleRepository.findById(id);

        if (roleDB.isPresent()) {
            Role roleUpdated = roleDB.get();
            roleUpdated.setRole(role.getRole());
            return Optional.of(roleRepository.save(roleUpdated));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
