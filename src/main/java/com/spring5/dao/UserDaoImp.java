package com.spring5.dao;

import com.google.common.collect.FluentIterable;
import org.springframework.stereotype.Repository;

import com.spring5.model.User;
import com.spring5.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return FluentIterable.from(userRepository.findAll()).toList();
    }
}
