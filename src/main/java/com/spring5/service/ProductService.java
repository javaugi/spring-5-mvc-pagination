/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface ProductService {

    void save(Product product);

    void saveAll(List<Product> products);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Iterable<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Iterable<Product> findAll(int offset, int limit);
}
