package com.way.gtool.common.error;

import com.way.gtool.common.utils.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        System.out.println("全局异常捕获：" + e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNotFound(NoResourceFoundException e, Model model) {
        System.out.println("全局异常捕获：" + e.getMessage());
        model.addAttribute("msg", "资源未找到");
        return "forward:/404";  // 返回模板页面
    }
}

