package com.spring5.dao;

import java.util.List;

import com.spring5.model.User;

public interface UserDao {

    void save(User user);

    List<User> findAll();
}
