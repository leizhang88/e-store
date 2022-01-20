package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(11);
        cart.setPid(10000011);
        cart.setNum(2);
        cart.setPrice(34L);
        cartMapper.insert(cart);
    }

    @Test
    public void countByUid() {
        cartMapper.updateNumByCid(1, 4, "admin", new Date());
    }

    @Test
    public void findByUidAndPid() {
        System.err.println(cartMapper.findByUidAndPid(11, 10000011));
    }

    @Test
    public void findVOByUid() {
        System.err.println(cartMapper.findVOByUid(11));
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOByCid() {
        Integer[] cids = {1, 3, 6, 12, 34};
        System.err.println(cartMapper.findVOByCid(cids));
    }
}
