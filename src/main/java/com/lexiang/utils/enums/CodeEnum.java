package com.lexiang.utils.enums;

/**
 * Created by mengke on 2017/12/5.
 */
public enum CodeEnum {
    SYSTEM_ERROR(500, "请求服务器异常"),
    NO_CONFIG_IMAGE_CODE(1009,"请配置imageCodeFlag"),
    SYSTEM_BUSINESS(1008,"请求服务繁忙"),
    IMAGE_CODE_ERROR(1200,"请求服务繁忙"),
    UPLOAD_FILE_ERROR(100001, "文件上传失败"),
    API_NAME_EXIST(100002, "接口名称已存在"),
    USER_NOT_LOGIN(999,"登录会话已过期，请重新登录"),
    RE_SEND_MSG_CODE(200501,"请重新发送短信验证码"),
    USER_NOT_LOGIN_IN_SYSTEM(999,"用户未登录"),
    LOGIN_NO_CONFIG(10004,"系统暂未配置登录，请前往配置"),
    API_NOT_EXIST(100003, "接口参数转换配置不存在"),
    ;

    private Integer code;
    private String name;

    CodeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }


    public String getName() {
        return name;
    }
}
