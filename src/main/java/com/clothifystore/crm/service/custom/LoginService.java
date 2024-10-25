package com.clothifystore.crm.service.custom;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.service.SuperService;

public interface LoginService extends SuperService {
    User existsUser(String emailId, String password);
}
