package com.CourseTodoCode.educationalplatform.service;

import com.CourseTodoCode.educationalplatform.model.UserEntity;
import com.CourseTodoCode.educationalplatform.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> update(Long id, UserEntity userEntity) {
        Optional<UserEntity> userDB = userRepository.findById(id);

        if (userDB.isPresent()) {
            UserEntity userUpdated = userDB.get();

            if (userRepository.findUserEntityByUsername(userUpdated.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Username already in use");
            }

            userUpdated.setUsername(userEntity.getUsername());
            return Optional.of(userRepository.save(userUpdated));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
