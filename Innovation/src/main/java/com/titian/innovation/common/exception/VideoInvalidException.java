package com.titian.innovation.common.exception;
import com.titian.innovation.common.http.ErrorCode;
public class VideoInvalidException extends BaseException {
    public VideoInvalidException() {
        super(ErrorCode.VIDEO_INVALID);
    }
}
