package com.example.demo;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.ObjectJsonService;
import com.example.demo.Service.RoleService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ObjectJsonService objectJsonService;
    @Test
    public void id() {
    ;
}
    @Test
    public void contextLoads() {

//        Account account=new Account();
//        account.setName("username");
//        account.setPassword("123456");
//
//        accountService.addAccount(account);
//        List<Account> accountList=accountRepository.findAll();
//        accountService.deleteAccountById(5);
        System.out.println(accountRepository.deleteAccountById(5));
//        Account account=accountRepository.findAccountByUsername("niran2");
//        System.out.println(objectJsonService.ObjectToJson(accountList));
//
//        roleRepository.save(role);
//        System.out.println(roleRepository.findRoleByAccount_Username("niran2").getName());
//        System.out.println( DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}
