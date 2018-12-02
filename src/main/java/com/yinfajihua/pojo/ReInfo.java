package com.yinfajihua.pojo;

public class ReInfo {
    private int status;
    private String message;
    private Object date;

    public ReInfo(int status,String message,Object obj){
        this.status = status;
        this.message = message;
        this.date = obj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
