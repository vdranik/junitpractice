package com.junitpractice.dao.impl;

import com.junitpractice.dao.OrderDao;
import com.junitpractice.dto.Order;

import java.sql.SQLException;

public class OrderDaoImpl  implements OrderDao{

    @Override
    public int create(Order order) {
        return 0;
    }

    @Override
    public Order read(int id) throws SQLException {
        return null;
    }

    @Override
    public int update(Order order) throws SQLException {
        return 0;
    }

    @Override
    public int delete(int id) throws SQLException {
        return 0;
    }
}
