package com.cy.store.service.impl;

import com.cy.store.controller.ex.CartNotFoundException;
import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException();
        }
        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException();
        }

        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if(rows != 1) {
            throw new UpdateException();
        }
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException();
        }
        if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException();
        }

        Integer num = result.getNum() - 1;
        if(num < 0) num = 0;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if(rows != 1) {
            throw new UpdateException();
        }
        return num;
    }
}
