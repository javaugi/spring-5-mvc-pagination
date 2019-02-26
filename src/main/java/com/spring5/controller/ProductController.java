/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.controller;

import com.spring5.service.ProductService;
import com.spring5.model.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Controller
@RequestMapping("/")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap modelMap) {
        List<Product> products = (List<Product>) productService.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(products);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        LOG.error("page {}", page);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        return "index";
    }

}
