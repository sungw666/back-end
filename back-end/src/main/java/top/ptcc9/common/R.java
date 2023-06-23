package top.ptcc9.common;

import top.ptcc9.utils.CommonUtil;


public class R<T> {
    private Integer statusCode;
    private Integer code;
    private String message;
    private Integer type;
    private T data;

    private R(State state, T data, String message) {
        this.statusCode = state.getStatusCode();
        this.code = state.getCode();
        this.message = CommonUtil.isEmpty(message) ? state.getMessage() : message;
        this.type = state.getType();
        this.data = data;
    }

    public static <T> R<T> build(State state, T t) {
        return build(state, t, null);
    }

    public static <T> R<T> build(State state, String message) {
        return build(state, null, message);
    }

    public static <T> R<T> build(State state) {
        return build(state, null, null);
    }

    public static <T> R<T> build(State state, T t, String message) {
        return new R<>(state, t, message);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
