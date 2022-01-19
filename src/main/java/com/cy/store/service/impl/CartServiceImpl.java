package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Product product = productMapper.findById(pid);
        if(result == null) { // The product hasn't been added to cart
            Cart cart = new Cart();
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setNum(amount);
            cart.setPrice(product.getPrice());
            cart.setModifiedUser(username);
            cart.setCreatedUser(username);
            cart.setModifiedTime(new Date());
            cart.setCreatedTime(new Date());
            Integer rows = cartMapper.insert(cart);

            if(rows != 1) throw new InsertException();
        } else {
            Integer newNum = amount + result.getNum();
            Integer rows = cartMapper.updateNumByCid(result.getCid(), newNum, username, new Date());
            if(rows != 1) throw new UpdateException();
        }
    }
}
