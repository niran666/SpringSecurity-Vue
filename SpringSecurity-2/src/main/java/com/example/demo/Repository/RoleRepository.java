package com.example.demo.Repository;

import com.example.demo.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
//    Role findRoleByName(String name);
//    Role findRoleByAccount_Username(String username);
    List<Role> findAll();
}
