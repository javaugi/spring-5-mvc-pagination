/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.repository;

import com.spring5.model.MailUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Repository
public interface MailUserRepository extends PagingAndSortingRepository<MailUser, Long> {

    @Query("SELECT u FROM MailUser u WHERE u.name= (:name)")
    List<MailUser> findByName(@Param("name") String name);

    @Query("SELECT u FROM MailUser u WHERE u.name= (:name)")
    Optional<MailUser> findByUsername(@Param("name") String name);
}
