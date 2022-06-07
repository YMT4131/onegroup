package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.vo.TreeModel;
import com.shanghai.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RequestMapping("module")
@Controller
public class ModuleController extends BaseController {
    @Resource
    private ModuleService moduleService;

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeModel> queryAllModules(Integer roleId){



        return moduleService.queryAllModules(roleId);
    }

    //进入授权页面
    @RequestMapping("toGrantPage")
    public String toGrantPage(Integer roleId,HttpServletRequest request){
        //将需要授权的角色的id放到请求域中
        request.setAttribute("roleId",roleId);
        return "role/grant";
    }

}
