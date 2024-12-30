package com.jason.gtool.domain.vo;

import com.jason.gtool.domain.type.Operate;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.handle.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:34
 * @desciption:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Op {
    private String name;
    private Operate op;

    /**
     * 根据路由获取操作列表用于显示在前端
     */
    public static List<Op> getOpsByRoute(RouteEnum route) {
        if (route == null) {//default route use json
            return new JSON().getOps();
        }
        return Route.routes().stream()
            .filter(routeEnum -> routeEnum.getValue() == route)
            .map(routeEnum -> routeEnum.getValue().getStrategy().getOps())
            .flatMap(List::stream).collect(Collectors.toList());
    }
}
