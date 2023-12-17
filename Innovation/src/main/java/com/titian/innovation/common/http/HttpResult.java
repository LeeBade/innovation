package com.titian.innovation.common.http;

import com.titian.innovation.item.domain.ItemInfo;
import com.titian.innovation.user.domain.UserInfo;

import java.util.HashMap;
import java.util.List;

public class HttpResult<T> extends HashMap<String,Object> {
    private static final String CODE_TAG = "code";
    private static final String ERROR_TAG="err";
    private static final String ITEMS_TAG = "items";
    private static final String USERINFO_TAG="userInfo";
    private static final String ITEMINFO_TAG="itemInfo";
    private HttpResult(){}
    /**返回错误信息*/
    private HttpResult(int code,String err){
        super.put (CODE_TAG,code);
        super.put (ERROR_TAG,err);
    }

    /**返回成功数据*/
    private HttpResult(int code,String tag,T t){
        super.put (CODE_TAG,code);
        super.put (tag,t);
    }

    public static HttpResult<List<Item>> success(List<Item> items){
        return new HttpResult<> (200,ITEMS_TAG,items);
    }

    public static HttpResult<ItemInfo> success(ItemInfo itemInfo){
        return new HttpResult<> (200,ITEMINFO_TAG,itemInfo);
    }
    public static HttpResult<UserInfo> success(UserInfo userInfo){
        return new HttpResult<> (200,USERINFO_TAG,userInfo);
    }

    public static HttpResult<String> fail(ErrorCode errorCode){
        return new HttpResult<> (errorCode.getCode (),errorCode.getErr ());
    }
}

