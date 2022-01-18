package com.cy.store.mapper;

import com.cy.store.entity.Cart;

import java.util.Date;

public interface CartMapper {
    /**
     * Insert a record into cart database
     * @param cart
     * @return
     */
    Integer insert(Cart cart);

    /**
     * Update the number of product in cart
     * @param cid The card id
     * @param num Number to be updated
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * Find records of cart based on user id and product id
     * @param uid
     * @param pid
     * @return
     */
    Cart findByUidAndPid(Integer uid, Integer pid);
}
