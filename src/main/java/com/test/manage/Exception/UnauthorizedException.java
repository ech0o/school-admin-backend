package com.test.manage.Exception;

public class UnauthorizedException extends AppException{

    public UnauthorizedException(String message){
        super(message);
    }

    public UnauthorizedException(String message,Throwable cause){
        super(message,cause);
    }
}
