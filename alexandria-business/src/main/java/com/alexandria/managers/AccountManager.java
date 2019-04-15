package com.alexandria.managers;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;

import java.util.List;

public interface AccountManager {
    List<OrderHeaderEntity> getOrdersList(ClientEntity client);
    List<OrderLineEntity> getOrderLines(OrderHeaderEntity order);
}