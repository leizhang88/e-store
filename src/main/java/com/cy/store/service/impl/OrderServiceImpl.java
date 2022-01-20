package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        List<CartVO> list = cartService.getVOByCid(uid, cids);
        // Calculate total price of order
        Long totalPrice = 0L;
        for(CartVO c : list) {
            totalPrice += c.getRealPrice() * c.getNum();
        }

        Address address = addressService.getByAid(aid, uid);

        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setOrderTime(new Date());

        order.setModifiedUser(username);
        order.setCreatedUser(username);
        order.setModifiedTime(new Date());
        order.setCreatedTime(new Date());

        Integer rows = orderMapper.insertOrder(order);
        if(rows != 1) throw new InsertException();

        // Create OrderItem object
        for(CartVO c : list) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getRealPrice());
            orderItem.setNum(c.getNum());
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());

            rows = orderMapper.insertOrderItem(orderItem);
            if(rows != 1) throw new InsertException();
        }

        return order;
    }
}
