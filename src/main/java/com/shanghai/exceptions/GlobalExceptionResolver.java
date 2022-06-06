package com.shanghai.exceptions;

import com.alibaba.fastjson.JSON;
import com.shanghai.base.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理类
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        //用户登录拦截异常处理
        if(e instanceof NoLoginException){
            //直接重定向到登录页面
            mv.setViewName("redirect:/login");
            return mv;
        }
        //默认异常处理

        mv.setViewName("404"); //跳转到404.html页面
        mv.addObject("code",400);
        mv.addObject("msg","系统异常，请重来一遍。。。");

        if(o instanceof HandlerMethod){
            HandlerMethod handlerMethod =(HandlerMethod)o;
            //获取方法上是否有ResponseBody注解
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            if(null == responseBody){
                //不是ResponseBody注解的方法返回的是视图
                if(e instanceof ParamsException){ //抛出的是自定义的异常
                    mv.addObject("code",((ParamsException) e).getCode());
                    mv.addObject("msg",((ParamsException) e).getMsg());
                }
            }else{
                //ResponseBody注解的方法返回的json
                ResultInfo resultInfo =new ResultInfo();
                resultInfo.setCode(400);
                resultInfo.setMsg("系统异常，请重来。。。。");
                if(e instanceof ParamsException){
                    resultInfo.setCode(((ParamsException) e).getCode());
                    resultInfo.setMsg(((ParamsException) e).getMsg());
                }
                /**
                 * 因为方法默认返回的是一个ModelAndView，对应json的返回值只能使用流来进行输出
                 */
                //设置响应头
                httpServletResponse.setContentType("application/json;charset=utf-8");

                PrintWriter writer = null;

                try {
                    writer = httpServletResponse.getWriter();
                    //将resultInfo转换为JSON字符串并用流写出
                    writer.write(JSON.toJSONString(resultInfo));
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    writer.close();
                }
                //用于结束方法，否则方法会返回一个ModelAndView
                return null;
            }
        }
        return mv;
    }
}
