package com.titian.innovation.common.exception;
import com.titian.innovation.common.http.ErrorCode;
public class ImageInvalidException extends BaseException {
    public ImageInvalidException() {
        super(ErrorCode.IMAGE_INVALID);
    }
}
