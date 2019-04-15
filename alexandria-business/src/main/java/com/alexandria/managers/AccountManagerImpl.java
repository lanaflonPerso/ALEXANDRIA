package com.alexandria.managers;

import com.alexandria.dao.OrderHeaderDao;
import com.alexandria.dao.OrderLineDao;
import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.OrderHeaderEntity;
import com.alexandria.entities.OrderLineEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountManagerImpl implements AccountManager {

    @Autowired
    OrderHeaderDao orderHeaderDao;

    @Autowired
    OrderLineDao orderLineDao;

    @Override
    public List<OrderHeaderEntity> getOrdersList(ClientEntity client) {
        return orderHeaderDao.findFromClient(client);
    }

    @Override
    public List<OrderLineEntity> getOrderLines(OrderHeaderEntity order) {
        List<OrderLineEntity> orderLines = orderLineDao.findFromOrderHeader(order);
        return orderLines;
    }
}
