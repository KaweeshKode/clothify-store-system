package com.clothifystore.crm.repository.custom.impl;

import com.clothifystore.crm.entity.UserEntity;
import com.clothifystore.crm.repository.custom.UserDao;
import com.clothifystore.crm.util.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
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
    public List<UserEntity> viewAll() {
        List<UserEntity> users = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            users = session.createQuery("FROM UserEntity", UserEntity.class).getResultList(); // HQL query to retrieve all UserEntity records
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
        return users;
    }

    @Override
    public boolean update(UserEntity entity) {
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
            UserEntity user = session.get(UserEntity.class, id); // retrieve the UserEntity by ID
            // If the user exists, delete it
            if (user != null) {
                session.delete(user);
                session.getTransaction().commit();
                return true;
            } else {
                new Alert(Alert.AlertType.WARNING, "User with ID " + id + " not found.").showAndWait();
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

    @Override
    public UserEntity findUserByEmailAndPassword(String emailId, String password) {
        UserEntity entity = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            // query to find user by email and password
            entity = session.createQuery("FROM UserEntity WHERE email = :emailId AND password = :password", UserEntity.class)
                    .setParameter("emailId", emailId)
                    .setParameter("password", password)
                    .uniqueResult();

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
        return entity;
    }
}
