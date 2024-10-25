package com.clothifystore.crm.service.custom.impl;

import com.clothifystore.crm.dto.User;
import com.clothifystore.crm.entity.UserEntity;
import com.clothifystore.crm.repository.DaoFactory;
import com.clothifystore.crm.repository.custom.UserDao;
import com.clothifystore.crm.service.custom.UserService;
import com.clothifystore.crm.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao repository = DaoFactory.getInstance().getDaoType(DaoType.USER);

    @Override
    public boolean addUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        return repository.save(entity);
    }

    @Override
    public List<User> viewUsers() {
        List<User> users = new ArrayList<>();
        List<UserEntity> userEntities = repository.viewAll();
        for (UserEntity userEntity : userEntities) {
            User user = new ModelMapper().map(userEntity, User.class);
            users.add(user);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        return repository.update(entity);
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return repository.delete(userId);
    }
}
