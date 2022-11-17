package com.tvc.network.response;

public class BaseResponse {

    private int code = -1;  // 服务器返回code

    private String msg;  // 服务器返回的消息
    private int state;
    private int total = 1;


    private Throwable throwable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return "server: " + msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String toString() {
        if (code == -1 && throwable != null) {
            return "RESPONSE err: " + throwable.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("RESPONSE: [msg:").append(msg).append(" state:").append(state).append(" total:").append(total).append("]");
        return sb.toString();
    }

    public static <T> T createErrorResponse(T response, Throwable throwable) {
        if (response instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) response;
            baseResponse.code = -1;
            baseResponse.throwable = throwable;
        }
        return response;
    }
}
