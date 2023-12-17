package com.titian.innovation.common.exception;

import com.titian.innovation.common.http.ErrorCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BaseException extends RuntimeException{
    public BaseException(ErrorCode errorCode){
        this.errorCode=errorCode;
    }
    private final ErrorCode errorCode;
}
