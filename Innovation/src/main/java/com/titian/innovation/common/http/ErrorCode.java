package com.titian.innovation.common.http;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ITEM_TOO_MUCH(10001, "最多只能创建三个项目哦，请进入项目详情页进行删除再返回创建"),
    CREATE_ITEM_DENIED(10002, "抱歉，您暂时不能创建项目，请联系管理员或老师申请权限哦！"),
    FILE_UPLOAD_FAILED(20001, "对不起，文件上传失败，请重新上传"),
    FILE_INVALID(20002, "请选择主流格式的演示文件（建议pdf），请重新上传"),
    VIDEO_INVALID(20003, "请选择主流格式的演示视频，请重新上传"),
    IMAGE_INVALID(20004, "请选择主流格式的图片，请重新上传"),
    FILE_NOT_FOUND(20005, "抱歉，目标文件已移动或删除");
    ErrorCode (int code, String err){
        this.code=code;
        this.err =err;
    }
    private final int code;
    private final String err;

}


