package com.clothifystore.crm.service.custom.impl;

import com.clothifystore.crm.dto.Supplier;
import com.clothifystore.crm.entity.SupplierEntity;
import com.clothifystore.crm.repository.DaoFactory;
import com.clothifystore.crm.repository.custom.SupplierDao;
import com.clothifystore.crm.service.custom.SupplierService;
import com.clothifystore.crm.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierDao repository = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        return repository.save(entity);
    }

    @Override
    public List<Supplier> viewSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        List<SupplierEntity> supplierEntities = repository.viewAll();
        for (SupplierEntity supplierEntity : supplierEntities) {
            suppliers.add(new ModelMapper().map(supplierEntity, Supplier.class));
        }
        return suppliers;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        return repository.update(entity);
    }

    @Override
    public boolean deleteSupplier(Integer supplierId) {
        return repository.delete(supplierId);
    }
}
