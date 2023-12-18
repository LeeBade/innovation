package com.titian.innovation.Authrioty;

import com.titian.innovation.common.exception.AccessDeniedException;
import lombok.Getter;

@Getter
public enum AccessLevel {
    NORMAL_USER(0,"普通用户"),
    LEADER(1,"组长"),//允许创建项目
    ADMIN(2,"管理员");//允许看到更改用户的accesslevel、admin访问我的项目时都是他的，因此他可以看到删除按钮、同时admin也能创建项目、
    //目前权限管理的设计是不好的，以后有时间再重构
    //管理员只能由后端设置

    AccessLevel(int level,String identify){
        this.level=level;
        this.identify=identify;
    }
    private final int level;
    private final String identify;
    public static domain.AccessLevel identify2AccessLevel (String identify){
        switch (identify){
            case("普通用户"):
                return NORMAL_USER;
            case("组长"):
                return LEADER;
            case("管理员"):
                return ADMIN;
            default:
                throw new AccessDeniedException ();
        }
    }
    public static domain.AccessLevel level2Accesslevel(int level){
        switch (level){
            case(0):
                return NORMAL_USER;
            case(1):
                return LEADER;
            case(2):
                return ADMIN;
            default:
                throw new AccessDeniedException ();
        }
    }
}
