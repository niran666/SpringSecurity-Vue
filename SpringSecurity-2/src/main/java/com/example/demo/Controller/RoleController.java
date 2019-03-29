package com.example.demo.Controller;


import com.example.demo.Service.AccountService;
import com.example.demo.Service.ObjectJsonService;
import com.example.demo.Service.RoleService;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.AccountTo;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController(value ="/role")
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectJsonService objectJsonService;
    @PreAuthorize("hasAnyAuthority('admin','user')")
    @RequestMapping(value ="/hello")
    public Result hello() {
        Account account = new Account();
//        Account account= (Account)SecurityContextHolder.getContext().getAuthentication().getPrincipal().g;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            account = accountService.findByName(currentUserName);
        }
        return new Result(account);
    }
//
    @RequestMapping(value = "/save",method= RequestMethod.POST)
    public Result hello(Role role) throws ServletException{
        if(roleService.addRole(role))
            return new Result(role);
        else
            throw new ServletException("修改失败");
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value ="/getAllUser",method= RequestMethod.GET)
    public Result getAllUser () throws ServletException{
        return new Result(objectJsonService.ObjectToJson(accountService.findAll()));
//        return new Result(accountService.findAll());
    }
    @RequestMapping(value ="/updateUser",method= RequestMethod.POST)
    public Result updateUserMessage (AccountTo accountTo) throws ServletException{

        Account account = objectJsonService.ToAccount(accountTo);
        if(accountService.updateAccount(account))
            return  new Result(account);
        throw new ServletException("修改失败");
//        return new Result(accountService.findAll());
    }


    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") Integer id) throws ServletException {
        logger.info("*******id="+id+"*****************");
        if(accountService.deleteAccountById(id)){
            return new Result(true,"删除成功","");
        }
        else {
            throw new ServletException("删除失败");
        }
    }

}
