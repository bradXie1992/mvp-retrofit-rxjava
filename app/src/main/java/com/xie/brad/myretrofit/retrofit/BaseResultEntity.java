package com.xie.brad.myretrofit.retrofit;

/**
 * 回调信息统一封装类
 *
 */
public class BaseResultEntity<T> {
    @Override
    public String toString() {
        return "BaseResultEntity{" +
                "result='" + result + '\'' +
                ", Code=" + Code +
                ", msg='" + msg + '\'' +
                ", line=" + line +
                ", data=" + data +
                '}';
    }

    /**
     * result : success
     * Code : 800
     * msg :
     * line : 77
     * debugInfo : {}
     * data : {"admin_name":"Tony.Zhang","admin_level":"2"}
     */

    private String result;
    private int Code;
    private String msg;
    private int line;

    //显示数据（用户需要关心的数据）
    private T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public T getData() {
        return data;
    }
}
