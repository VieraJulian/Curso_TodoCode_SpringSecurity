package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserEntity> findAll();
    public Optional<UserEntity> findById(Long id);
    public UserEntity save(UserEntity userEntity);
    public Optional<UserEntity> update(Long id, UserEntity userEntity);
    public void deleteById(Long id);
    public String encryptPassword(String password);
}
