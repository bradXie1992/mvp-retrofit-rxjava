package com.xie.brad.myretrofit.retrofit;

/**
 * 自定义错误信息，统一处理返回处理
 *
 */
public class HttpTimeException extends RuntimeException {

    public static final int NO_DATA = 0x2;

    public HttpTimeException(int resultCode, String detailMessage) {
        this(getApiExceptionMessage(resultCode, detailMessage));
    }

    public HttpTimeException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 转换错误数据
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code, String detailMessage) {
        String message = "";
        switch (code) {
            case NO_DATA:
                message = "无数据";
                break;
            default:
                message = detailMessage;
                break;
        }
        return message;
    }
}

