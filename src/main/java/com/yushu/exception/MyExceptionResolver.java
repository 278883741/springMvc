package com.yushu.exception;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @Nullable Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ex",e);
        modelAndView.setViewName("error/error");
        if(e instanceof MyException){
            // 打印日志
            modelAndView.setViewName("error/myError");
        }
        return modelAndView;
    }
}
