package com.spring5.dao;

import com.spring5.model.Contact;
import java.util.List;

import com.spring5.model.MailUser;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface MailUserDao {

    void save(MailUser user);

    void saveAll(List<MailUser> users);

    Optional<MailUser> findById(Long id);

    public List<MailUser> findAll();

    Iterable<MailUser> findAll(int offset, int limit);

    Iterable<MailUser> findAll(Sort sort);

    Page<MailUser> findAll(Pageable pageable);

    List<MailUser> findByName(String name);

    List<MailUser> findByName(String name, int limit, int offset);
}
