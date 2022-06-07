package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.Role;
import com.shanghai.po.vo.AlbumModel;
import com.shanghai.query.RoleQuery;
import com.shanghai.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;


    /**
     * 跳转到专辑管理页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "role/role_index";
    }

    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(RoleQuery roleQuery){

        return roleService.queryByParamsForTable(roleQuery);

    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("添加成功");
    }

    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer roleId, HttpServletRequest request){
        if (roleId != null){
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("role",role);
        }

        return "role/role_add_update";
    }

    @PostMapping("updateRole")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("添加成功");
    }





}
