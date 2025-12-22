package com.way.gtool.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.way.gtool.common.utils.Result;
import com.way.gtool.domain.req.AuthPram;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:04
 * @desciption: 认证接口
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${gtool.auth.appKey}")
    private String appKey;
    @PostMapping("/login")
    public Result execute(@RequestBody AuthPram param) {
        if (appKey.equals(param.getAppKey())) {
            StpUtil.login(appKey);
            return Result.ok("认证成功");
        }else {
            return Result.get(401, "认证失败，appKey错误", null);
        }
    }
}
