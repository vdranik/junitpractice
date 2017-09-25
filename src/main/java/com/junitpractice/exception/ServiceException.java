package com.junitpractice.exception;

import java.sql.SQLException;

public class ServiceException extends Exception {

    public ServiceException(SQLException e) {
        super(e);
    }
}
