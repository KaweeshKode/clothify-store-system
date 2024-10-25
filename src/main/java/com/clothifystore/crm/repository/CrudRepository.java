package com.clothifystore.crm.repository;

import java.util.List;

public interface CrudRepository<T> extends SuperDao{
    boolean save(T entity);
    List<T> viewAll();
    boolean update(T entity);
    boolean delete(Integer id);
}
