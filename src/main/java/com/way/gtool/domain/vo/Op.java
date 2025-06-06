package com.way.gtool.domain.vo;

import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.type.RouteEnum;
import com.way.gtool.handle.JSON;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JingWei Guo
 * @date 2024/12/28 13:34
 * @desciption:
 */
public class Op {
    private String name;
    private Operate op;

    public Op(String name, Operate op) {
        this.name = name;
        this.op = op;
    }

    public Op() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operate getOp() {
        return op;
    }

    public void setOp(Operate op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return "Op{" +
                "name='" + name + '\'' +
                ", op=" + op +
                '}';
    }

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
