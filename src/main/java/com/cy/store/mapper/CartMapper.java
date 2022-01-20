package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;

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

    List<CartVO> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(Integer[] cids);
}
