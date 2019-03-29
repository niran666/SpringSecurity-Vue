package com.example.demo.Service.Impl;


import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Service.RoleService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

//    @Override
//    public Role findRoleByAccount_Username(String username){
//}
//
    @Override
    public Boolean addRole(Role role){
        Account account=new Account();
//        EntityGraph graph = accountRepository.getEntityGraph("graph.Department.employees");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            account = accountRepository.findAccountByUsername(currentUserName);
        }
        if(account.getRole()!=null) {
            role.setId((account.getRole()).getId());
            if(roleRepository.save(role)!=null)
                return true;
            else
                return false;
        }
        roleRepository.save(role);
        account.setRole(role);
        if(accountRepository.save(account)!=null)
            return true;
        else
            return false;
    }
    public List<Role> findAllUsers(){
        return roleRepository.findAll();
    }
}