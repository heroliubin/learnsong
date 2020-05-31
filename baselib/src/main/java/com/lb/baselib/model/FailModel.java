package com.lb.baselib.model;

public class FailModel {
    private String msg;
    private String type;
    private int code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public FailModel(String msg, String type, int code) {
        this.msg = msg;
        this.type = type;
        this.code = code;
    }

    public FailModel() {
    }

    @Override
    public String toString() {
        return "FailModel{" +
                "msg='" + msg + '\'' +
                ", type='" + type + '\'' +
                ", code=" + code +
                '}';
    }
}
