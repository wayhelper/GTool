package com.way.gtool.domain.req;

import com.way.gtool.domain.type.RouteEnum;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:29
 * @desciption: 分享请求入参
 */

public class SharePram {
    private RouteEnum route;
    private String data;

    public RouteEnum getRoute() {
        return route;
    }

    public void setRoute(RouteEnum route) {
        this.route = route;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SharePram{" +
                "route=" + route +
                ", data='" + data + '\'' +
                '}';
    }
}
