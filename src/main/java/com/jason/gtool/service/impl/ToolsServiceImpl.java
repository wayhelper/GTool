package com.jason.gtool.service.impl;

import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.domain.vo.Route;
import com.jason.gtool.service.IToolsService;
import com.jason.gtool.utils.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:06
 * @desciption: 对于路由取缓存，避免了重复创建对象操作。
 */
@Service
public class ToolsServiceImpl implements IToolsService {

    @Override
    public Result route(GDoPram param) {
        return param.getRoute().getStrategy().execute(param.getOp(), param.getData());
    }

    @Override
    @Cacheable(cacheNames = "routes")
    public Result getRoutes() {
        return Result.get(200, "success",Route.routes());
    }

    @Override
    @Cacheable(cacheNames = "routeOptions", key = "#param")
    public Result getReouteOptions(RouteEnum param) {
        return Result.get(200, "success", Op.getOpsByRoute(param));
    }
}
