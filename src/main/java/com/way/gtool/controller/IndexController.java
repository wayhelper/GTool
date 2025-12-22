package com.way.gtool.controller;

import com.way.gtool.domain.req.SharePram;
import com.way.gtool.domain.type.RouteEnum;
import com.way.gtool.service.IToolsService;
import com.way.gtool.common.utils.ShareCache;
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

    @GetMapping("/404")
    public String page404(Model model) {
        model.addAttribute("pageTitle", "404 - Drive Page");
        model.addAttribute("exitUrl", "https://alal.site");
        model.addAttribute("weather", Math.random() > 0.5 ? "rain" : "fog");
        model.addAttribute("ghosts", 12);
        model.addAttribute("mapSize", 400);
        return "404";
    }

    @GetMapping("/share/{sid}")
    public String share(@PathVariable("sid") int sid, Model model) {
        model.addAttribute("routes", toolsService.getRoutes().getData());
        model.addAttribute("ops", toolsService.getReouteOptions(RouteEnum.JSON).getData());

        SharePram share = shareCache.get(sid);
        if (share == null) {
            model.addAttribute("share", "分享链接已失效");
            model.addAttribute("ro", "JSON");
        } else {
            model.addAttribute("share", share.getData());
            model.addAttribute("ro", share.getRoute());
            shareCache.del(sid);
        }

        return "tools";
    }

}
