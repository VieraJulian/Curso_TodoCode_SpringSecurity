package com.CourseTodoCode.educationalplatform.repository;

import com.CourseTodoCode.educationalplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
