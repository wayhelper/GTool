package com.way.gtool.domain.vo;

import com.way.gtool.domain.type.RouteEnum;

import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:12
 * @desciption:
 */

public class Route {
    private String name;
    private RouteEnum value;

    public Route(String name, RouteEnum value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RouteEnum getValue() {
        return value;
    }

    public void setValue(RouteEnum value) {
        this.value = value;
    }

    /**
     * 获取所有路由
     * @return
     */
    public static List<Route> routes() {
        return Arrays.asList(
            new Route("JSON校验", RouteEnum.JSON),
            new Route("Base64", RouteEnum.BASE64),
            new Route("Unicode转码",RouteEnum.UNICODE),
            new Route("字母大小写",RouteEnum.CASE),
            new Route("SQL美化",RouteEnum.SQL),
            new Route("时间戳转换",RouteEnum.UNIX),
            new Route("加解密",RouteEnum.CRYPTION),
            new Route("翻译",RouteEnum.TRANSLATE),
            new Route("二维码", RouteEnum.QRCODE)/*,
            new Route("TTL", RouteEnum.TTL)*/
        );
    }
}
