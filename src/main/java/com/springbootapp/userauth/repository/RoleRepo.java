package com.springbootapp.userauth.repository;

import com.springbootapp.userauth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long>{
}