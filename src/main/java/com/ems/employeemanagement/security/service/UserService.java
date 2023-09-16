package com.ems.employeemanagement.security.service;

import com.ems.employeemanagement.security.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(), "Amit Vikram", "amit@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Harsh Vikram", "harsh@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Ankit Kumar", "ankitgmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Harini Giridhar", "harini@gmail.com"));

    }
    public List<User> getUsers(){
        return this.store;
    }


}
