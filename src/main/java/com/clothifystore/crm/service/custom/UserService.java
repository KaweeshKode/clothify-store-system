package com.clothifystore.crm.service.custom;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.service.SuperService;

public interface UserService extends SuperService {
    boolean addUser(User user);
}
