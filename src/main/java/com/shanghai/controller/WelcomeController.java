package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.service.WelcomeService;
import com.shanghai.vo.Welcome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("welcome")
public class WelcomeController extends BaseController {
    @Resource
    private WelcomeService welcomeService;

    @RequestMapping("index")
    public String welcomeInfo(HttpServletRequest request){
        Welcome welcome=welcomeService.welcomeInfo();
        request.setAttribute("welcome",welcome);
        return "/welcome";
    }
}
