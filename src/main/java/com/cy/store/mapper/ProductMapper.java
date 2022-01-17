package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findPopularList();

    Product findById(Integer id);
}
