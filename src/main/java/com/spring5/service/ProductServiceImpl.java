/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.dao.ProductRepository;
import com.spring5.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

}
