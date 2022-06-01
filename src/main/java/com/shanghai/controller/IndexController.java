package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.User;
import com.shanghai.po.vo.UserModel;
import com.shanghai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公有功能实现
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    //访问模板首页
    @RequestMapping("indexModel")
    public String toIndex(){
        return "indexModel";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("login")
    public String toLoginPage(){
        return "login";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("main")
    public String toMainPage(){
        return "main";
    }

    /**
     * 主页中默认显示的页面
     * @return
     */
    @RequestMapping("welcome")
    public String toWelcomePage(){
        return "welcome";
    }

    /**
     * 登录操作
     * @param userModel
     * @return
     */
    @RequestMapping("loginAction")
    @ResponseBody
    public ResultInfo loginAction(@RequestBody UserModel userModel){
        return userService.login(userModel);
    }



}
