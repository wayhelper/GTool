package com.way.gtool.domain.type;

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
    //下划线&空格转驼峰
    CAMEL,
    //驼峰转下划线
    UNDERLINE,
    //驼峰转空格
    SPACE,
    //美化mysql
    MYSQL,
    //美化oracle
    ORACLE,
    //美化postgres
    PGSQL,
    //日期转时间戳
    TIMESTAMP,
    //时间戳转日期
    DATE,
    //加密md5
    EMD5,
    //加密des
    EDES,
    //解密des
    DDES,
    //转中文
    TOCN,
    //转英文
    TOEN,
    //生成二维码
    ENQRCODE,
    //解码二维码
    DEQRCODE;
}
