package com.clothifystore.crm.service.custom;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    boolean addUser(User user);
    List<User> viewUsers();
    boolean updateUser(User user);
    boolean deleteUser(Integer userId);
}
