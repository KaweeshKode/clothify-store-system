package com.clothifystore.crm.service.custom.impl;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.service.custom.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean addUser(User user) {
        System.out.println(user);
        return true;
    }
}
