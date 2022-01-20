package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;

public interface OrderMapper {
    /**
     * Insert a record of order
     * @param order
     * @return Affected number of records.
     */
    Integer insertOrder(Order order);

    /**
     * Isert a record of order item
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);
}
