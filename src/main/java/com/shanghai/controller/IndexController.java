package com.shanghai.controller;

import com.shanghai.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    //访问首页
    @RequestMapping("index")
    public String toIndex(){
        return "/index";
    }
}
