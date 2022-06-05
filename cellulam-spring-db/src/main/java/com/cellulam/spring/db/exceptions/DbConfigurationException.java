package com.cellulam.spring.db.exceptions;

import com.cellulam.core.exceptions.DbException;

public class DbConfigurationException extends DbException {
    public DbConfigurationException(String msg) {
        super(msg);
    }

    public DbConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
