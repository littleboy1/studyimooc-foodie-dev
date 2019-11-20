package com.lzq.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResult {

    /**
     *     定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     *  响应业务状态码
     */
    private Integer status;
    /**
     * 响应的消息数据
     */
    private String msg;
    /**
     * 应中的数据
     */
    private Object data;
    /**
     * // 不使用
     */
    @JsonIgnore
    private String ok;

    public JsonResult() {
    }

    public JsonResult(Object data) {
        this.data = data;
        this.status =200;
        this.msg = "OK";
    }

    public JsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public Boolean isOK() {
        return this.status == 200;
    }
    public static JsonResult build(Integer status, String msg, Object data) {
        return new JsonResult(status, msg, data);
    }

    public static JsonResult build(Integer status, String msg, Object data, String ok) {
        return new JsonResult(status, msg, data, ok);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult errorMsg(String msg) {
        return new JsonResult(500, msg, null);
    }

    public static JsonResult errorMap(Object data) {
        return new JsonResult(501, "error", data);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(502, msg, null);
    }

    public static JsonResult errorException(String msg) {
        return new JsonResult(555, msg, null);
    }

    public static JsonResult errorUserQQ(String msg) {
        return new JsonResult(556, msg, null);
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", ok='" + ok + '\'' +
                '}';
    }
}
