package com.jason.gtool.service.impl;

import com.jason.gtool.domain.req.GDoPram;
import com.jason.gtool.common.utils.Result;
import com.jason.gtool.common.utils.ShareCache;
import com.jason.gtool.domain.req.SharePram;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.domain.vo.Route;
import com.jason.gtool.service.IToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:06
 * @desciption: 对于固定的路由取缓存，避免了重复创建对象操作。
 */
@Service
public class ToolsServiceImpl implements IToolsService {

    @Value("${gtool.host}")
    private String host;

    private final ShareCache shareCache;

    private static final AtomicInteger shareIdCounter = new AtomicInteger(10000);

    @Autowired
    public ToolsServiceImpl(ShareCache shareCache) {
        this.shareCache = shareCache;
    }

    @Override
    public Result route(GDoPram param) {
        return param.getRoute().getStrategy().execute(param.getOp(), param.getData());
    }

    @Override
    @Cacheable(cacheNames = "routes")
    public Result getRoutes() {
        return Result.get(200, SUCCESS_MSG, Route.routes());
    }

    @Override
    @Cacheable(cacheNames = "routeOptions", key = "#param")
    public Result getReouteOptions(RouteEnum param) {
        return Result.get(200, SUCCESS_MSG, Op.getOpsByRoute(param));
    }

    @Override
    public Result share(SharePram param) {
        int sid = shareIdCounter.incrementAndGet();
        this.shareCache.set(sid, param);
        String shareUrl = host + "/share/" + sid;
        return Result.get(200, SHARE_SUCCESS_MSG, shareUrl);
    }

    // 添加常量，提高可读性和可维护性
    private static final String SUCCESS_MSG = "success";
    private static final String SHARE_SUCCESS_MSG = "复制成功! 5 分钟后过期";
}