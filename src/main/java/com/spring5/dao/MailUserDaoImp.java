package com.spring5.dao;

import com.google.common.collect.FluentIterable;
import org.springframework.stereotype.Repository;

import com.spring5.model.MailUser;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.spring5.repository.MailUserRepository;

@Repository
public class MailUserDaoImp implements MailUserDao {

    @Autowired
    private MailUserRepository mailUserRepository;

    @Override
    public void save(MailUser user) {
        mailUserRepository.save(user);
    }

    @Override
    public void saveAll(List<MailUser> users) {
        mailUserRepository.saveAll(users);
    }

    @Override
    public Optional<MailUser> findById(Long id) {
        return mailUserRepository.findById(id);
    }

    @Override
    public Optional<MailUser> findByUsername(String username) {
        return mailUserRepository.findByUsername(username);
    }

    @Override
    public List<MailUser> findAll() {
        return FluentIterable.from(mailUserRepository.findAll()).toList();
    }

    @Override
    public Iterable<MailUser> findAll(int offset, int limit) {
        return mailUserRepository.findAll();
    }

    @Override
    public Iterable<MailUser> findAll(Sort sort) {
        return mailUserRepository.findAll(sort);
    }

    @Override
    public Page<MailUser> findAll(Pageable pageable) {
        return mailUserRepository.findAll(pageable);
    }

    @Override
    public List<MailUser> findByName(String name) {
        return mailUserRepository.findByName(name);
    }

    @Override
    public List<MailUser> findByName(String name, int limit, int offset) {
        return mailUserRepository.findByName(name);
    }
}
