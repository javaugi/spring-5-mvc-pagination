package com.spring5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.dao.UserDao;
import com.spring5.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public void saveAll(List<User> users) {
        userDao.saveAll(users);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll(int offset, int limit) {
        return userDao.findAll(offset, limit);
    }

    @Transactional(readOnly = true)
    public Iterable<User> findAll(Sort sort) {
        return userDao.findAll(sort);
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }
}
