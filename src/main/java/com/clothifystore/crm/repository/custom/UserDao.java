package com.clothifystore.crm.repository.custom;

import com.clothifystore.crm.entity.UserEntity;
import com.clothifystore.crm.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity> {
    UserEntity findUserByEmailAndPassword(String emailId, String password);
}
