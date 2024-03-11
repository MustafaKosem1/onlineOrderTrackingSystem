package com.galaksiya.demoProject.repository;

import com.galaksiya.demoProject.entity.User;

import java.util.List;

public interface IUserRepository {
    void save(User user);

    User getById(int id);

    List<User> getAll();

    void update(User user);

    void delete(int id);

    User getByUserName(String userName);
}
