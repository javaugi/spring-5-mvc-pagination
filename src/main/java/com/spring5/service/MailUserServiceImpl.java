package com.spring5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.model.MailUser;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.spring5.dao.MailUserDao;

@Service
public class MailUserServiceImpl implements MailUserService {

    @Autowired
    private MailUserDao mailUserDao;

    @Transactional
    public void save(MailUser user) {
        mailUserDao.save(user);
    }

    @Transactional
    public void saveAll(List<MailUser> users) {
        mailUserDao.saveAll(users);
    }

    @Transactional(readOnly = true)
    public Optional<MailUser> findById(Long id) {
        return mailUserDao.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<MailUser> findByUsername(String username) {
        return mailUserDao.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Iterable<MailUser> findAll() {
        return mailUserDao.findAll();
    }

    @Transactional(readOnly = true)
    public Iterable<MailUser> findAll(int offset, int limit) {
        return mailUserDao.findAll(offset, limit);
    }

    @Transactional(readOnly = true)
    public Iterable<MailUser> findAll(Sort sort) {
        return mailUserDao.findAll(sort);
    }

    @Transactional(readOnly = true)
    public Page<MailUser> findAll(Pageable pageable) {
        return mailUserDao.findAll(pageable);
    }
}
