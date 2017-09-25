package com.junitpractice.service;

import com.junitpractice.dto.Order;
import com.junitpractice.exception.ServiceException;

public interface OrderService {

    boolean placeOrder(Order order) throws ServiceException;
    boolean cancelOrder(int id)  throws ServiceException;
    boolean deleteOrder(int id) throws ServiceException;
}
