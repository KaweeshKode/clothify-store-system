package com.clothifystore.crm.service.custom.impl;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.entity.UserEntity;
import com.clothifystore.crm.repository.DaoFactory;
import com.clothifystore.crm.repository.custom.UserDao;
import com.clothifystore.crm.service.custom.LoginService;
import com.clothifystore.crm.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class LoginServiceImpl implements LoginService {
    UserDao repository = DaoFactory.getInstance().getDaoType(DaoType.USER);

    @Override
    public User existsUser(String emailId, String password) {
        UserEntity userEntity = repository.findUserByEmailAndPassword(emailId, password);
        User dto = new ModelMapper().map(userEntity, User.class);
        return dto;
    }
}
