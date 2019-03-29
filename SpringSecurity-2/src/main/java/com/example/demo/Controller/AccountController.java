package com.example.demo.Controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.Service.AccountService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    public Result hello() {
//        Account account= (Account)SecurityContextHolder.getContext().getAuthentication().getPrincipal().g;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//
//            return new Result(currentUserName);
//        }
        return new Result("");
    }

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public void login(HttpServletResponse response)throws IOException {
    }

    @RequestMapping(value ="/register",method=RequestMethod.POST)
//    public Result register(@RequestParam("username") String username,@RequestParam("password") String password){
    public Result register(Account account) throws ServletException {
        if(accountService.addAccount(account)==1){
            return new Result("注册成功");
        }
        else{
            throw new ServletException("注册失败");
        }
    }
}
