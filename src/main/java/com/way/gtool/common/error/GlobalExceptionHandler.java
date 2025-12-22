package com.way.gtool.common.error;

import cn.dev33.satoken.exception.NotLoginException;
import com.way.gtool.common.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        if (e instanceof GToolException) {
            System.out.println("自定义异常捕获：" + e.getMessage());
            return Result.error(e.getMessage());
        } else if (e instanceof NotLoginException) {
            return Result.get(401, "未登录或登录已过期，请重新登录", null);
        }
        System.out.println("全局异常捕获：" + e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleNotFound(NoResourceFoundException e) {
        System.out.println("全局异常捕获！未找到资源：" + e.getMessage());
        ModelAndView mv = new ModelAndView("404");
        mv.addObject("msg", "资源未找到");
        return mv;
    }
}

