package com.clothifystore.crm.service.custom;

import com.clothifystore.crm.dto.Supplier;
import com.clothifystore.crm.service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    List<Supplier> viewSuppliers();
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(Integer supplierId);
}
