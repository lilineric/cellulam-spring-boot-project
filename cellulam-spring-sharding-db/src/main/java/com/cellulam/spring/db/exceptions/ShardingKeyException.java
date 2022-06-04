package com.cellulam.spring.db.exceptions;

import com.cellulam.core.exceptions.DbException;

public class ShardingKeyException extends DbException {
    public ShardingKeyException(String msg) {
        super(msg);
    }

    public ShardingKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
