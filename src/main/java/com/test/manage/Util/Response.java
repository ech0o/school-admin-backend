package com.test.manage.Util;


public class Response {

    private Integer code;

    private String msg;

    private Object obj;

    private Response(){ }

    private Response(int code, String msg, Object obj){
        this.code = code;
        this.msg=msg;
        this.obj= obj;
    }

    public static Response success(String msg){
        return new Response(200, msg, null);
    }

    public static Response success(String msg, Object obj){
        return  new Response(200, msg, obj);
    }

    public static Response fail(String msg){
        return new Response(500, msg,null);
    }

    public static Response fail(String msg, Object obj){
        return new Response(500,msg,obj);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
