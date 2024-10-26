package com.clothifystore.crm.service.custom;

import com.clothifystore.crm.dto.Product;
import com.clothifystore.crm.service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(Product product);
    List<Product> viewProducts();
    boolean updateProduct(Product product);
    boolean deleteProduct(Integer productId);
}
