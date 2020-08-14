package com.lexiang.utils.result;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.Map;

public class ActionResponse<E> {
    private int code;
    private String message;
    private E data;
    private Map<String ,Object> extend;

    public ActionResponse() {
        this.code = ResponseCode.code_SUCC;
    }

    public ActionResponse(int code, String message) {
        this.code = ResponseCode.code_SUCC;
        this.code = code;
        this.message = message;
    }

    public ActionResponse(int code, int businessCode, String message) {
        this.code = ResponseCode.code_SUCC;
        this.code = code;
        this.message = message;
    }

    public ActionResponse(E result) {
        this.code = ResponseCode.code_SUCC;
        this.data = result;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public boolean succ() {
        return this.code == ResponseCode.code_SUCC;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public ActionResponse Ok() {
        this.code = ResponseCode.code_SUCC;
        return this;
    }

    public ActionResponse Error() {
        this.code = ResponseCode.code_500;
        return this;
    }

    public ActionResponse Error(Integer code) {
        this.code = code;
        return this;
    }

    public ActionResponse Error(String message) {
        this.message = message;
        return this;
    }

    public ActionResponse Error(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ActionResponse Extend(Map<String ,Object> extend) {
        this.extend = extend;
        return this;
    }
}
