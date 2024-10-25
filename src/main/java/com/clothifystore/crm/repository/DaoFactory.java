package com.clothifystore.crm.repository;

import com.clothifystore.crm.repository.custom.impl.UserDaoImpl;
import com.clothifystore.crm.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance==null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type) {
        switch (type) {
            case USER: return (T) new UserDaoImpl();
        }
        return null;
    }
}
