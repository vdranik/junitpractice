package com.junitpractice.service.impl;

import com.junitpractice.dao.OrderDao;
import com.junitpractice.dto.Order;
import com.junitpractice.exception.ServiceException;
import com.junitpractice.service.OrderService;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Override
    public boolean placeOrder(Order order) throws ServiceException {

        try {
            int result = orderDao.create(order);
            if(result == 0) return false;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }

        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws ServiceException {

        try {
            Order order = orderDao.read(id);
            order.setStatus("cancelled");
            int result = orderDao.update(order);
            if(result == 0) return false;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }

        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws ServiceException {

        try {
            int result = orderDao.delete(id);
            if(result == 0) return false;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }

        return true;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
