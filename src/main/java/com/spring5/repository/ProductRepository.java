/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.repository;

import com.spring5.model.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name= (:name)")
    Optional<Product> findByName(String name);
}
