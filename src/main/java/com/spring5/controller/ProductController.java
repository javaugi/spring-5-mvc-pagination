/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.controller;

import com.google.common.collect.FluentIterable;
import com.spring5.service.ProductService;
import com.spring5.model.Product;
import com.spring5.type.DisplayCriteria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Controller
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    static final String[] PROD_NAMES = {"Abundance", "Acclimaze", "Accruex", "Adornica", "Aerosol Cheese", "Aftertizer",
        "Airborne Pickle", "AirHead", "Alumina", "Apple Cheeks", "Baby Donuts", "Bag of Scones", "Bath and Relax",
        "Botox Barbie", "Brand Dandy", "Bris-o-matic", "Brush n Flush", "Bum Bait", "Buster Boon", "Callflex",
        "Data Basket", "Deal Light", "Demo Lotion", "Develaport", "DialUp", "Diamond Sky", "Diet Smokes", "DigiGate"};
    static final List<String> PROD_LIST = Arrays.asList(PROD_NAMES);

    private static int pageSize = 5;
    private int totalRecords = 0;
    private String entry;
    private String search;

    @Autowired
    private ProductService productService;

    List<Product> allProducts;

    @PostConstruct
    public void init() {
        createProducts();
        Iterable<Product> productIterable = productService.findAll();
        allProducts = FluentIterable.from(productIterable).toList();
        totalRecords = allProducts.size();
        long pages = totalRecords / pageSize;
        LOG.info("products total {} pages total {} with page size {}", totalRecords, pages, pageSize);
    }

    /*
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap modelMap) {
        List<Product> products = (List<Product>) productService.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(products);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        return "product/index";
    }
    // */
    @GetMapping("/listProducts")
    public String listProducts(HttpServletRequest request, ModelMap modelMap) {
        return doDisplay(request, modelMap);
    }

    private String doDisplay(HttpServletRequest request, ModelMap modelMap) {
        int size = pageSize;
        String search = "";

        DisplayCriteria criteria = (DisplayCriteria) request.getSession().getAttribute("displayCriteria");
        if (criteria == null) {
            criteria = new DisplayCriteria();
        } else {
            search = criteria.getSearch();
            size = criteria.getEntry();
        }
        final String searchParam = search;
        if (size <= 0) {
            size = pageSize;
        }
        criteria.setEntry(size);
        criteria.setSearch(search);

        List<Product> products = allProducts;
        if (!search.isEmpty()) {
            products = allProducts.stream()
                    .filter(line -> line.getName().contains(searchParam) || line.getDescription().contains(searchParam))
                    .collect(Collectors.toList());
        }
        LOG.error("queryString {}", request.getQueryString());

        PagedListHolder pagedListHolder = new PagedListHolder(products);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        LOG.info("listProducts of page {} size {} displayCriteria {}", page, size, criteria);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(size);
        modelMap.put("pagedListHolder", pagedListHolder);
        modelMap.put("entryList", getEntryList());
        modelMap.put("displayCriteria", criteria);

        return "listProducts";
    }

    @PostMapping("/updateEntry")
    public String updateEntry(@ModelAttribute("displayCriteria") DisplayCriteria criteria, HttpServletRequest request, ModelMap modelMap) {
        DisplayCriteria criteriaOrig = (DisplayCriteria) request.getSession().getAttribute("displayCriteria");
        if (criteriaOrig != null) {
            if (criteria.getEntry() <= 0) {
                criteria.setEntry(criteriaOrig.getEntry());
            }
            if (criteria.getSearch() == null || criteria.getSearch().isEmpty()) {
                criteria.setSearch(criteriaOrig.getSearch());
            }
        }
        request.getSession().setAttribute("displayCriteria", criteria);
        LOG.error("updateEntry criteria {}", criteria);
        return doDisplay(request, modelMap);
    }

    @GetMapping("/pagedProducts")
    public String getPagedProducts(HttpServletRequest request, ModelMap modelMap) {
        PagedListHolder pagedListHolder = new PagedListHolder(allProducts);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        LOG.info("pagedProducts of page {}", page);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(pageSize);
        modelMap.put("pagedListHolder", pagedListHolder);
        return "listProducts";
    }

    private List<Product> createProducts() {

        List<Product> returnValue = new ArrayList();
        Product product = null;
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            product = new Product();
            product.setName(PROD_LIST.get(rand.nextInt(PROD_LIST.size())));
            product.setPrice(new BigDecimal(rand.nextDouble() * 100));
            product.setQuantity(rand.nextInt(20));
            product.setStatus(true);
            product.setDescription(PROD_LIST.get(rand.nextInt(PROD_LIST.size())));
            returnValue.add(product);
        }

        productService.saveAll(returnValue);
        return returnValue;
    }

    public Map<String, String> getEntryList() {
        Map<String, String> entryList = new HashMap<String, String>();
        entryList.put("5", "5");
        entryList.put("8", "8");
        entryList.put("10", "10");
        return entryList;
    }

    public String getEntry() {
        LOG.error("entry {}", entry);
        return entry;
    }

    public void setEntry(String entry) {
        LOG.error("set entry {}", entry);
        this.entry = entry;
    }

    public String getSearch() {
        LOG.error("search {}", search);
        return search;
    }

    public void setSearch(String search) {
        LOG.error("set search {}", search);
        this.search = search;
    }

}
