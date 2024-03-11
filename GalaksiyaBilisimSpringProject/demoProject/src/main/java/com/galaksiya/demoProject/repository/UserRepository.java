package com.galaksiya.demoProject.repository;

import com.galaksiya.demoProject.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository{

    private EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(int id) {
        User user=entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query=entityManager.createQuery("select u from User u", User.class);
        List<User> userList=query.getResultList();
        return userList;
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        User user=getById(id);
        entityManager.remove(user);
    }

    @Override
    public User getByUserName(String userName) {
        TypedQuery<User> query=entityManager.createQuery("from User where username=:data",User.class);
        query.setParameter("data",userName);
        return query.getSingleResult();
    }
}
