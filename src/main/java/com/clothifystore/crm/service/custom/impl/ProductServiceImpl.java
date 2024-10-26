package com.clothifystore.crm.service.custom.impl;

import com.clothifystore.crm.dto.Product;
import com.clothifystore.crm.entity.ProductEntity;
import com.clothifystore.crm.repository.DaoFactory;
import com.clothifystore.crm.repository.SuperDao;
import com.clothifystore.crm.repository.custom.ProductDao;
import com.clothifystore.crm.service.custom.ProductService;
import com.clothifystore.crm.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao repository = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    @Override
    public boolean addProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        return repository.save(entity);
    }

    @Override
    public List<Product> viewProducts() {
        List<Product> products = new ArrayList<>();
        List<ProductEntity> productEntities = repository.viewAll();
        for (ProductEntity productEntity : productEntities) {
            Product product = new ModelMapper().map(productEntity, Product.class);
            products.add(product);
        }
        return products;
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        return repository.update(entity);
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        return repository.delete(productId);
    }
}
