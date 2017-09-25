package com.junitpractice.test.service;


import com.junitpractice.dao.OrderDao;
import com.junitpractice.dto.Order;
import com.junitpractice.exception.ServiceException;
import com.junitpractice.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;
    private OrderServiceImpl orderService;
    private static final int ORDER_ID = 123;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        orderService = new OrderServiceImpl();
        orderService.setOrderDao(orderDao);
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, ServiceException {

//        Order order = new Order();
        when(orderDao.create(any(Order.class))).thenReturn(new Integer(1));

        boolean result = orderService.placeOrder(any(Order.class));
        assertTrue(result);
        verify(orderDao, times(1)).create(any(Order.class));
    }

    @Test
    public void placeOrder_Should_NOT_Create_An_Order() throws SQLException, ServiceException {

//        Order order = new Order();
        when(orderDao.create(any(Order.class))).thenReturn(new Integer(0));

        boolean result = orderService.placeOrder(any(Order.class));
        assertFalse(result);
        verify(orderDao, atLeast(1)).create(any(Order.class));
    }

    @Test(expected = ServiceException.class)
    public void placeOrder_Should_Throw_ServiceException() throws SQLException, ServiceException {

        Order order = new Order();
        when(orderDao.create(order)).thenThrow(SQLException.class);
        boolean result = orderService.placeOrder(order);
        verify(orderDao, atLeastOnce()).create(order);
    }

    @Test
    public void cancelOrder_Should_Cancel_The_Order() throws SQLException, ServiceException {

        Order order = new Order();
        when(orderDao.read(anyInt())).thenReturn(order);
        when(orderDao.update(order)).thenReturn(1);

        boolean result = orderService.cancelOrder(anyInt());                //before we use 123
        assertTrue(result);

        verify(orderDao, atMost(1)).read(anyInt());   //before we use 123
        verify(orderDao, times(1)).update(order);  //before we use 123
    }

    @Test
    public void cancelOrder_Should_NOT_Cancel_The_Order() throws SQLException, ServiceException {

        Order order = new Order();
        when(orderDao.read(123)).thenReturn(order);
        when(orderDao.update(order)).thenReturn(0);

        boolean result = orderService.cancelOrder(123);
        assertFalse(result);

        verify(orderDao, times(1)).read(123);
        verify(orderDao, times(1)).update(order);
    }

    @Test(expected = ServiceException.class)
    public void cancelOrder_Should_ThrowAServiceExceptionOnRead() throws SQLException, ServiceException {

        when(orderDao.read(123)).thenThrow(SQLException.class);
        orderService.cancelOrder(123);

        verify(orderDao).read(123);
    }

    @Test(expected = ServiceException.class)
    public void cancelOrder_Should_ThrowAServiceExceptionOnUpdate() throws SQLException, ServiceException {

        Order order = new Order();

        when(orderDao.read(ORDER_ID)).thenReturn(order);
        when(orderDao.update(order)).thenThrow(SQLException.class);
        orderService.cancelOrder(ORDER_ID);

        verify(orderDao).read(1234);
        verify(orderDao).update(order);
    }

    @Test
    public void deleteOrder_Deletes_The_Order() throws SQLException, ServiceException {
        when(orderDao.delete(ORDER_ID)).thenReturn(new Integer(ORDER_ID));

        boolean result = orderService.deleteOrder(ORDER_ID);
        assertTrue(result);
        verify(orderDao).delete(ORDER_ID);

    }

}
