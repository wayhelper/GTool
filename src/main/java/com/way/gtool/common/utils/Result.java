package com.way.gtool.common.utils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author JingWei Guo
 * @date 2024/12/27 17:04
 * @desciption:
 */
public class Result extends LinkedHashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public Result(Map<String, ?> map) {
        this.setMap(map);
    }

    public Integer getCode() {
        return (Integer)this.get("code");
    }

    public String getMsg() {
        return (String)this.get("msg");
    }

    public Object getData() {
        return this.get("data");
    }

    public Result setCode(int code) {
        this.put("code", code);
        return this;
    }

    public Result setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    public Result setData(Object data) {
        this.put("data", data);
        return this;
    }

    public Result set(String key, Object data) {
        this.put(key, data);
        return this;
    }

    public <T> T get(String key, Class<T> cs) {
        return null;
    }

    public Result setMap(Map<String, ?> map) {
        Iterator var2 = map.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            this.put(key, map.get(key));
        }

        return this;
    }

    public Result setJsonString(String jsonString) {
        return null;
    }

    public Result removeDefaultFields() {
        this.remove("code");
        this.remove("msg");
        this.remove("data");
        return this;
    }

    public Result removeNonDefaultFields() {
        Iterator var1 = this.keySet().iterator();

        while(var1.hasNext()) {
            String key = (String)var1.next();
            if (!"code".equals(key) && !"msg".equals(key) && !"data".equals(key)) {
                this.remove(key);
            }
        }

        return this;
    }

    public static Result ok() {
        return new Result(200, "ok", (Object)null);
    }

    public static Result ok(String msg) {
        return new Result(200, msg, (Object)null);
    }

    public static Result code(int code) {
        return new Result(code, (String)null, (Object)null);
    }

    public static Result data(Object data) {
        return new Result(200, "ok", data);
    }

    public static Result error() {
        return new Result(500, "error", (Object)null);
    }

    public static Result error(String msg) {
        return new Result(500, msg, (Object)null);
    }

    public static Result get(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result empty() {
        return new Result();
    }

    public String toString() {
        return "{\"code\": " + this.getCode() + ", \"msg\": " + this.transValue(this.getMsg()) + ", \"data\": " + this.transValue(this.getData()) + "}";
    }

    private String transValue(Object value) {
        if (value == null) {
            return null;
        } else {
            return value instanceof String ? "\"" + value + "\"" : String.valueOf(value);
        }
    }
}