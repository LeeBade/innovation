package com.titian.innovation.common.exception;

import com.titian.innovation.common.http.ErrorCode;

public class FileInvalidException extends BaseException {
    public FileInvalidException() {
        super(ErrorCode.FILE_INVALID);
    }
}