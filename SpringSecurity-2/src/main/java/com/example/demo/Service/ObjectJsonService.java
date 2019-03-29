package com.example.demo.Service;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.AccountTo;

import java.util.List;

public interface ObjectJsonService {

    JSONArray ObjectToJson(List<Account> accountList);

    Account ToAccount(AccountTo accountTo);
}
