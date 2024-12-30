package com.jason.gtool.service;

import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.utils.Result;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:06
 * @desciption:
 */
public interface IToolsService {

    /**
     * 工具路由方法
     * @param param
     * @return
     */
    Result route(GDoPram param);

    /**
     * 获取路由列表
     * @return
     */
    Result getRoutes();

    /**
     * 获取路由选项
     * @param param
     * @return
     */
    Result getReouteOptions(RouteEnum param);
}
