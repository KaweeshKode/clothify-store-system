package com.clothifystore.crm.repository.custom.impl;

import com.clothifystore.crm.entity.ProductEntity;
import com.clothifystore.crm.entity.UserEntity;
import com.clothifystore.crm.repository.custom.ProductDao;
import com.clothifystore.crm.util.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(entity); // saves the entity, generating an ID if needed
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<ProductEntity> viewAll() {
        List<ProductEntity> products = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            products = session.createQuery("FROM ProductEntity", ProductEntity.class).getResultList(); // HQL query to retrieve all ProductEntity records
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    @Override
    public boolean update(ProductEntity entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.merge(entity); // update the existing entity in the database
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id); // retrieve the ProductEntity by ID
            // If the product exists, delete it
            if (product != null) {
                session.delete(product);
                session.getTransaction().commit();
                return true;
            } else {
                new Alert(Alert.AlertType.WARNING, "Product with ID " + id + " not found.").showAndWait();
                return false;
            }
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
