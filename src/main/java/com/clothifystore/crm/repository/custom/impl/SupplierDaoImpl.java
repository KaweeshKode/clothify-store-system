package com.clothifystore.crm.repository.custom.impl;

import com.clothifystore.crm.entity.SupplierEntity;
import com.clothifystore.crm.repository.custom.SupplierDao;
import com.clothifystore.crm.util.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
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
    public List<SupplierEntity> viewAll() {
        List<SupplierEntity> suppliers = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            suppliers = session.createQuery("FROM SupplierEntity", SupplierEntity.class).getResultList(); // HQL query to retrieve all SupplierEntity records
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
        return suppliers;
    }

    @Override
    public boolean update(SupplierEntity entity) {
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
            SupplierEntity supplier = session.get(SupplierEntity.class, id); // retrieve the UserEntity by ID
            // If the supplier exists, delete it
            if (supplier != null) {
                session.delete(supplier);
                session.getTransaction().commit();
                return true;
            } else {
                new Alert(Alert.AlertType.WARNING, "Supplier with ID " + id + " not found.").showAndWait();
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
