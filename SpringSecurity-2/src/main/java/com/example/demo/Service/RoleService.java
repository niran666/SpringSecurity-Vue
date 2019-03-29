package com.example.demo.Service;

import com.example.demo.pojo.Role;

import java.util.List;

public interface RoleService {
//    Role findRoleByAccount_Username(String username);
    Boolean addRole(Role role);
    List<Role> findAllUsers();
}
