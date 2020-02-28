package com.test.manage.Exception;

import lombok.extern.slf4j.Slf4j;


public class AppException extends RuntimeException {


    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
