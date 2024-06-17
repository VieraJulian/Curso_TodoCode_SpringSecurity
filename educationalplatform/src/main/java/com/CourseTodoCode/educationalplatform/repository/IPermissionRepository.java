package com.CourseTodoCode.educationalplatform.repository;

import com.CourseTodoCode.educationalplatform.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
