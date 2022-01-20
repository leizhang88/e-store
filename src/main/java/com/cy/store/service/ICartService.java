package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * Add product to cart
     * @param uid
     * @param pid
     * @param amount
     * @param username The user who does the adding
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVO> getVOByUid(Integer uid);

    /**
     * Update product number in cart
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer addNum(Integer cid, Integer uid, String username);

    Integer reduceNum(Integer cid, Integer uid, String username);
}
