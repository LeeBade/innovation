package com.titian.innovation.common.http;

import com.titian.innovation.common.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(BaseException.class)
    public HttpResult<String> errorHandler(HttpServletRequest request, BaseException e){
        log.error (e.toString ());
        return HttpResult.fail (e.getErrorCode ());
    }
}