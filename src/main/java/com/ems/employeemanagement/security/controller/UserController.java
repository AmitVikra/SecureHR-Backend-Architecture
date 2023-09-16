package com.ems.employeemanagement.security.controller;

import com.ems.employeemanagement.security.model.User;
import com.ems.employeemanagement.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    // http://localhost:8080/home/user
    @GetMapping("/all-user")
    public List<User> getUser(){
        System.out.println("Getting users");
        return userService.getUsers();
    }
    // http://localhost:8080/home/current-user
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
