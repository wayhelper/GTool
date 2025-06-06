package com.way.gtool.controller;

import com.way.gtool.domain.req.GDoPram;
import com.way.gtool.domain.req.RoutePram;
import com.way.gtool.domain.req.SharePram;
import com.way.gtool.service.IToolsService;
import com.way.gtool.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:04
 * @desciption:
 */

@RestController
@RequestMapping("/tools")
public class ToolsController {
    @Autowired
    IToolsService toolsService;
    @PostMapping("/execute")
    public Result execute(@RequestBody GDoPram param) {
        return this.toolsService.route(param);
    }

    @PostMapping("/route")
    public Result route(@RequestBody RoutePram param) {
        return this.toolsService.getReouteOptions(param.getRoute());
    }

    @PostMapping("/share")
    public Result share(@RequestBody SharePram param) {
        return this.toolsService.share(param);
    }
}
