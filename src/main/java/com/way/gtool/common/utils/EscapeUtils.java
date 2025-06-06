package com.way.gtool.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author JingWei Guo
 * @date 2024/12/30 11:32
 * @desciption: 转义工具类
 */
public class EscapeUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private EscapeUtils() {
    }

    /**
     * Provide a public static method to obtain an ObjectMapper instance
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }


    /**
     * @param data
     * @return
     */
    public static String escape(String data) {
        try {
            return OBJECT_MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to escape data: " + data, e);
        }
    }

    /**
     * @param data
     * @return
     */
    public static String unescape(String data) {
        try {
            return OBJECT_MAPPER.readValue(data, String.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to unescape data: " + data, e);
        }
    }

}
