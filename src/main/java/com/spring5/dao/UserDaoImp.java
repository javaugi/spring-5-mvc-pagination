package com.spring5.dao;

import com.google.common.collect.FluentIterable;
import org.springframework.stereotype.Repository;

import com.spring5.model.User;
import com.spring5.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return FluentIterable.from(userRepository.findAll()).toList();
    }

    @Override
    public Iterable<User> findAll(int offset, int limit) {
        return userRepository.findAll();
    }

    @Override
    public Iterable<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByName(String name, int limit, int offset) {
        return userRepository.findByName(name);
    }
}
