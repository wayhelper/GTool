package com.way.gtool.domain.req;

import com.way.gtool.domain.type.Operate;
import com.way.gtool.domain.type.RouteEnum;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:29
 * @desciption: 请求入参
 */
public class GDoPram {
    private RouteEnum route;
    private Operate op;
    private String data;

    public GDoPram() {
    }

    public GDoPram(RouteEnum route, Operate op, String data) {
        this.route = route;
        this.op = op;
        this.data = data;
    }

    public RouteEnum getRoute() {
        return route;
    }

    public void setRoute(RouteEnum route) {
        this.route = route;
    }

    public Operate getOp() {
        return op;
    }

    public void setOp(Operate op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GDoPram{" +
                "route=" + route +
                ", op=" + op +
                ", data='" + data + '\'' +
                '}';
    }
}
