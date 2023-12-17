package com.titian.innovation.common.exception;
import com.titian.innovation.common.http.ErrorCode;

public class FileUploadFailedException extends BaseException {
    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAILED);
    }
}
