package com.cellulam.spring.core.exceptions;

import com.cellulam.core.exceptions.ConfigurationException;

public class PropertiesConfigureException extends ConfigurationException {
    public PropertiesConfigureException(String msg) {
        super(msg);
    }

    public PropertiesConfigureException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
