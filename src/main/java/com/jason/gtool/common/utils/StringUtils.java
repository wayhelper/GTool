package com.jason.gtool.common.utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

    // 将下划线或空格转为驼峰命名法
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        // 如果已经是驼峰格式，直接返回
        if (isCamelCase(input)) {
            return input;
        }
        // 使用正则表达式将输入中的下划线或空格分割
        String[] words = input.split("[-_\\s]");

        // 使用 IntStream 处理带索引的情况
        return IntStream.range(0, words.length)
                .mapToObj(i -> i == 0 ? words[i].toLowerCase() : capitalize(words[i]))
                .collect(Collectors.joining());
    }
    // 判断字符串是否是驼峰命名法
    private static boolean isCamelCase(String input) {
        return input != null && input.matches("^[a-z]+([A-Z][a-z]*)*$");
    }
    // 将首字母大写
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    // 将驼峰命名法转为空格分隔的字符串
    public static String camelToSpace(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // 使用正则表达式将大写字母前面加上空格
        return input.replaceAll("([a-z])([A-Z])", "$1 $2").toLowerCase();
    }

    // 将驼峰命名法转为下划线命名法
    public static String camelToUnderscore(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // 使用正则表达式将大写字母前面加上下划线
        return input.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}
