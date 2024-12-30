package com.jason.gtool.utils;

import cn.hutool.json.JSONUtil;

/**
 * @author JingWei Guo
 * @date 2024/12/30 11:32
 * @desciption: 转义工具类
 */
public class EscapeUtils {
    /**
     * escape
     * @param data
     * @return
     */
    public static String escape(String data) {
        return JSONUtil.escape(data);
    }
    /**
     * remove escape
     * @param data
     * @return
     */
    public static String unescape(String data) {
        return data.replace("\\\"", "\"")
            .replace("\\\\", "\\")
            .replace("\\/", "/")
            .replace("\\b", "\b")
            .replace("\\f", "\f")
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t");
    }
}
