package com.jason.gtool.controller;

import com.jason.gtool.domain.req.SharePram;
import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.service.IToolsService;
import com.jason.gtool.common.utils.ShareCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author JingWei Guo
 * @date 2024/12/27 15:19
 * @desciption:
 */
@Controller
public class IndexController {
    
    private final IToolsService toolsService;
    private final ShareCache shareCache;

    @Autowired
    public IndexController(IToolsService toolsService, ShareCache shareCache) {
        this.toolsService = toolsService;
        this.shareCache = shareCache;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("routes", this.toolsService.getRoutes().getData());
        model.addAttribute("ops", this.toolsService.getReouteOptions(RouteEnum.JSON).getData());
        return "tools";
    }
    @GetMapping("/share/{sid}")
    public String share(@PathVariable("sid") int sid, Model model) {
        model.addAttribute("routes", this.toolsService.getRoutes().getData());
        model.addAttribute("ops", this.toolsService.getReouteOptions(RouteEnum.JSON).getData());
        SharePram share = this.shareCache.get(sid);
        if (share == null) {
            model.addAttribute("share", "分享链接已失效");
            model.addAttribute("ro", "JSON");
            return "tools";
        }
        model.addAttribute("share", this.shareCache.get(sid).getData());
        model.addAttribute("ro", this.shareCache.get(sid).getRoute());
        return "tools";
    }

}
