package com.example.demo.Service;


import com.example.demo.pojo.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    int addAccount(Account account);
    Account findByName(String username);
    List<Account> findAll();
    Boolean updateAccount(Account account);
    Boolean deleteAccountById(Integer id);
}
