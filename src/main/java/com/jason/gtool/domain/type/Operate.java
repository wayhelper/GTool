package com.jason.gtool.domain.type;

/**
 * @author JingWei Guo
 * @date 2024/12/26 17:54
 * @desciption: 操作枚举
 */
public enum Operate {
    // 加密
    ENCRYPT,
    // 解密
    DECRYPT,
    // 格式化
    FORMAT,
    // 压缩
    DENSITY,
    // 转义
    ESCAPE,
    // 反转义
    UNESCAPE,
    //转大写
    CAPITAL,
    //转小写
    LOWER,
    //美化mysql
    MYSQL,
    //美化oracle
    ORACLE,
    //美化postgres
    PGSQL;
}
