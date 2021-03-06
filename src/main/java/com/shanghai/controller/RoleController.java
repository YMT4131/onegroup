package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.Role;
import com.shanghai.po.vo.AlbumModel;
import com.shanghai.query.RoleQuery;
import com.shanghai.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    //添加角色
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role){
        roleService.addRole(role);
        return success("添加成功");
    }
    //跳转添加或编辑页面
    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer roleId, HttpServletRequest request){
        if (roleId != null){
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("role",role);
        }

        return "role/role_add_update";
    }
    //更新
    @PostMapping("updateRole")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("修改成功");
    }

    //删除
    @PostMapping("deleteRole")
    @ResponseBody
    public ResultInfo deleteRole(Integer[] ids){
        roleService.deleteRole(ids);
        return success("删除成功");
    }

    @PostMapping("addRoleGrant")
    @ResponseBody
    public ResultInfo addRoleGrant(Integer roleId,@RequestBody Integer[] ids){
        roleService.addRoleGrant(roleId,ids);

        return success("角色授权成功");

    }

}
