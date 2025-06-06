package com.way.gtool.domain.req;

import com.way.gtool.domain.type.RouteEnum;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:29
 * @desciption: 请求入参
 */

public class RoutePram {
    private RouteEnum route;

    public RouteEnum getRoute() {
        return route;
    }

    public void setRoute(RouteEnum route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "RoutePram{" +
                "route=" + route +
                '}';
    }
}
