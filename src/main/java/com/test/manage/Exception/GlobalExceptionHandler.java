package com.test.manage.Exception;

import com.test.manage.Util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response OnException(Exception e) {
        logErrorMessage(e);
        return Response.fail(getExceptionMessage(e));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response onEntityNotFoundException(Exception e){
        logErrorMessage(e);
        return Response.fail(getExceptionMessage(e));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response onIllegalArgumentException(Exception e){
        log.warn(e.getMessage());
        return Response.fail("the parameter is wrong");
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response onUnauthorizedException(Exception e){
        log.warn("unauthorized access");
        return Response.fail("you don't have the permission to access");
    }

    private String logErrorMessage(Exception e) {
        log.error("Caught exception while handling a request", e);
        return e.getClass().getSimpleName();
    }

    private String getExceptionMessage(Exception e) {
        return StringUtils.hasText(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
    }
}
