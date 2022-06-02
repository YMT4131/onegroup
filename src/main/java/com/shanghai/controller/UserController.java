package com.shanghai.controller;

import com.shanghai.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @RequestMapping("index")
    public String index(){
        return "user/member";
    }
}
