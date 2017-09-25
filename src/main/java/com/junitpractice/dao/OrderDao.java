package com.junitpractice.dao;

import com.junitpractice.dto.Order;

import java.sql.SQLException;

public interface OrderDao {

    int create(Order order) throws SQLException;
    Order read(int id) throws SQLException;
    int update(Order order) throws SQLException;
    int delete(int id) throws SQLException;
}
