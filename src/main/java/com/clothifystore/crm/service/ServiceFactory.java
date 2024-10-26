package com.clothifystore.crm.service;

import com.clothifystore.crm.service.custom.impl.LoginServiceImpl;
import com.clothifystore.crm.service.custom.impl.ProductServiceImpl;
import com.clothifystore.crm.service.custom.impl.UserServiceImpl;
import com.clothifystore.crm.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance==null ? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type) {
        switch (type) {
            case LOGIN: return (T) new LoginServiceImpl();
            case USER: return (T) new UserServiceImpl();
            case PRODUCT: return (T) new ProductServiceImpl();
        }
        return null;
    }
}
