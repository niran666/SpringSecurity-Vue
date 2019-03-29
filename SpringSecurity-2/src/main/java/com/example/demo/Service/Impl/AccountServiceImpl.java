package com.example.demo.Service.Impl;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Service.AccountService;
import com.example.demo.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public int addAccount(Account account) {
        account.setPassword(DigestUtils.md5DigestAsHex(account.getPassword().getBytes()));
        System.out.println(account.getPassword());
        if(accountRepository.save(account)!=null){
            return 1;
        }
        else{
            return 0;
        }

    }
    public Account findByName(String username)
    {
        Account account=accountRepository.findAccountByUsername(username);
        return account;
    }
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    @Override
    public Boolean updateAccount(Account account){
        Account account2 = accountRepository.findAccountById(account.getId());
        if(account2!=null)
        {
            account.setUsername(account2.getUsername());
            account.setPassword(account2.getPassword());
            account.setIdentity(account2.getIdentity());
            if(account2.getRole()!=null)
            {
                account.getRole().setId(account2.getRole().getId());
            }
            account=accountRepository.save(account);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean deleteAccountById(Integer id){
        if(accountRepository.deleteAccountById(id)==1)
            return true;
        else
            return false;
    }
}
