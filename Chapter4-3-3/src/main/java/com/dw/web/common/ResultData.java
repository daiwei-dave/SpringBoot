package com.dw.web.common;

/**
 * 返回数据 的 封装对象
 *
 * @param <T>
 * @author fukui@gome.com.cn
 */
public class ResultData<T> implements java.io.Serializable {

    public static final String SUCCESS = ErrorCode.SUCCESS;// 返回码
    private static final long serialVersionUID = 0L;
    private String code = SUCCESS;// 返回码

    private String message;// 错误描述

    private T data;// 数据对象

    public ResultData() {
    }

    public ResultData(String code) {
        this.code = code;
        this.message = ErrorCode.SUCCESS_MSG;
    }

    public ResultData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(T data, String code) {
        this.data = data;
        this.code = code;
    }

    public ResultData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultData<T> newSuccessResultData() {
        return new ResultData<T>();
    }

    public static <T> ResultData<T> newResultData(String code) {
        return new ResultData<T>(code);
    }

    public static <T> ResultData<T> newResultData(String code, String message) {
        return new ResultData<T>(code, message);
    }

    public static <T> ResultData<T> newResultData(T data) {
        return new ResultData<T>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MSG, data);
    }

    public static <T> ResultData<T> newResultData(String code, String message, T data) {
        return new ResultData<T>(code, message, data);
    }

    public static boolean isSuccess(ResultData t) {
        return (t != null && SUCCESS.equals(t.getCode()));
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        if (ErrorCode.SUCCESS.equals(code)) {
            if (message==null){
                message = ErrorCode.SUCCESS_MSG;
            }
        }else{
            if (message == null) {
                message = ErrorCode.FAILOR_MSG;
            }
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //    public boolean isSuccessful() {
//        return SUCCESS.equals(this.code);
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResultData [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(getMessage());
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}
