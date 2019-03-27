/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.model.MailUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface MailUserService {

    void save(MailUser user);

    void saveAll(List<MailUser> users);

    Optional<MailUser> findById(Long id);

    Iterable<MailUser> findAll();

    Iterable<MailUser> findAll(int offset, int limit);

    public Iterable<MailUser> findAll(Sort sort);

    public Page<MailUser> findAll(Pageable pageable);
}
