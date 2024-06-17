package com.CourseTodoCode.educationalplatform.repository;

import com.CourseTodoCode.educationalplatform.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findUserEntityByUsername(String username);
}
