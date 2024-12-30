package com.jason.gtool.domain.vo;

import com.jason.gtool.domain.type.RouteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:12
 * @desciption:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private String name;
    private RouteEnum value;

    /**
     * 获取所有路由
     * @return
     */
    public static List<Route> routes() {
        return Arrays.asList(
            new Route("JSON校验", RouteEnum.JSON),
            new Route("Base64", RouteEnum.BASE64),
            new Route("Unicode转码",RouteEnum.UNICODE)
        );
    }
}
