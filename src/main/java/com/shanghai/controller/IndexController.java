package com.shanghai.controller;

import com.shanghai.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

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
     * 进入歌曲管理界面
     * @return
     */
    @RequestMapping("toSongManagePage")
    public String toSongManagePage(){
        return "musicManager/songManage";
    }

}
