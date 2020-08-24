package com.lexiang.utils.exception;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



public class BusinessException extends RuntimeException {
    private int code;
    private String codeStr;

    public BusinessException(String var1) {
        super(var1);
        this.code = 500;
    }

    public BusinessException(int var1, String var2) {
        super(var2);
        this.code = var1;
    }

    public BusinessException(int var1, String var2, String var3) {
        super(var3);
        this.code = var1;
        this.codeStr = var2;
    }

    public BusinessException(String var1, String var2) {
        super(var2);
        this.codeStr = var1;
    }

    public BusinessException(int var1, String var2, Throwable var3) {
        super(var2, var3);
    }

    public BusinessException(String var1, String var2, Throwable var3) {
        super(var2, var3);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int var1) {
        this.code = var1;
    }

    public String getCodeStr() {
        return this.codeStr;
    }

    public void setCodeStr(String var1) {
        this.codeStr = var1;
    }

    public String toString() {
        return "BusiException [code=" + this.code + ", codeStr=" + this.codeStr + ", message=" + this.getMessage() + "]";
    }
}
