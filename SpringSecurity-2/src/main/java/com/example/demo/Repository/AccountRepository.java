package com.example.demo.Repository;

import com.example.demo.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUsername(String username);
    List<Account> findAll();
    Account findAccountById(Integer id);
    @Transactional
    int deleteAccountById(Integer id);
}
