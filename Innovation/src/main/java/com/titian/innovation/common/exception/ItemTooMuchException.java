package com.titian.innovation.common.exception;

import com.titian.innovation.common.http.ErrorCode;

public class ItemTooMuchException extends BaseException{
    public ItemTooMuchException(){
        super(ErrorCode.ITEM_TOO_MUCH);
    }
}
