package com.android.retrofitdemo.network;

/**
 * 创建者：tyj
 * 创建于：2018/8/28
 * 说明：
 */

public class Result<T> {
    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    private int status;
    private String remark;
    private T data;

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getRemark() {
        return remark;
    }
}
