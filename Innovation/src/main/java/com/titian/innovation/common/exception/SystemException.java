package com.titian.innovation.common.exception;
import com.titian.innovation.common.http.ErrorCode;
public class FileSystemException extends BaseException {
    public FileSystemException () {
        super(ErrorCode.FILE_NOT_FOUND);
    }
}
