package com.jason.gtool.controller;

import com.jason.gtool.domain.type.RouteEnum;
import com.jason.gtool.domain.vo.Op;
import com.jason.gtool.domain.vo.Route;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author JingWei Guo
 * @date 2024/12/27 15:19
 * @desciption:
 */
@Controller
public class IndexController {
    @PostConstruct
    private List<Route> routes(){
        return Arrays.asList(
            new Route("JSON校验", RouteEnum.JSON),
            new Route("Base64", RouteEnum.BASE64),
            new Route("Unicode转码",RouteEnum.UNICODE)
        );
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("routes", this.routes());
        model.addAttribute("ops", Op.getOpsByRoute(null));
        return "tools";
    }
}
