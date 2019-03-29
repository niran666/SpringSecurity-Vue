package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.ObjectJsonService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.AccountTo;
import com.example.demo.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectJsonServiceImpl implements ObjectJsonService {
    @Override
    public JSONArray ObjectToJson(List<Account> accountList){
        JSONArray jsonArray=new JSONArray();
        for(Account account:accountList)
        {
            Role role=account.getRole();
            JSONObject RoleJson = (JSONObject)JSONObject.toJSON(role);
            JSONObject AccountJson = (JSONObject)JSONObject.toJSON(account);
            JSONObject jsonThree = new JSONObject();
            jsonThree.putAll(AccountJson);
            if(role!=null)
            {
                jsonThree.putAll( RoleJson);
            }
            jsonThree.putAll(AccountJson);
            jsonThree.remove("role");
            jsonThree.remove("password");
            jsonArray.add(jsonThree);
        }
        return jsonArray;
    }

    @Override
    public Account ToAccount(AccountTo accountTo){
        Role role =new Role();
        Account account=new Account();
        account.setUsername(accountTo.getUsername());
        account.setStatus(accountTo.getStatus());
        account.setId(accountTo.getId());
        role.setName(accountTo.getName());
        role.setAge(accountTo.getAge());
        role.setAddress(accountTo.getAddress());
        role.setSex(accountTo.getSex());
        account.setRole(role);
        return account;
    }
}
