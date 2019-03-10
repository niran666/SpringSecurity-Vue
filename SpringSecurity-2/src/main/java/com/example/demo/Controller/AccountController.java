package com.example.demo.Controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.Service.AccountService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value ="/hello", method = RequestMethod.GET)
    public Result hello() {

        return new Result("username:123");
    }

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public void login(HttpServletResponse response)throws IOException {
        logger.info("************非法访问*******************");
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
//        response.setHeader("Access-Control-Expose-Headers","*");
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.getWriter().write(JSON.toJSONString(new Result(false,"非法访问")));
    }

    @ResponseBody
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
