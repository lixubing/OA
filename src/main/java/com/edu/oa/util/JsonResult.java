package com.edu.oa.util;

/**
 * 返回前端信息的工具类
 */
public final class JsonResult {
    private boolean success = true;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonResult(){}
    public JsonResult(String msg){
        this.msg = msg;
    }
    public static JsonResult SUCCESS(){
        return new JsonResult();
    }
    public static JsonResult SUCCESS(String msg){
        JsonResult js = new JsonResult();
        js.setMsg(msg);
        return js;
    }
    public static JsonResult ERROR(){
        JsonResult js = new JsonResult();
        js.setSuccess(false);
        return js;
    }
    public static JsonResult ERROR(String msg){
        JsonResult js = new JsonResult();
        js.setSuccess(false);
        js.setMsg(msg);
        return js;
    }
}
