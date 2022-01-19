package com.cy.store.service;

public interface ICartService {
    /**
     * Add product to cart
     * @param uid
     * @param pid
     * @param amount
     * @param username The user who does the adding
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

}
