package com.example.demo.se;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component("myAuthenctiationSuccessHandler")
public class MyAuthenctiationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("登录成功");
        Account account=new Account();
        String _authorities="";
//        EntityGraph graph = accountRepository.getEntityGraph("graph.Department.employees");
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            account.setUsername(currentUserName);
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
               _authorities += grantedAuthority.getAuthority();
            }
//            account.setIdentity(authentication.getAuthorities());
            account.setIdentity(_authorities);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        response.setHeader("Access-Control-Expose-Headers","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.getWriter().write( JSON.toJSONString(new Result(true,"登陆成功",account)));
    }
}