package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.User;
import com.shanghai.po.vo.UserModel;
import com.shanghai.query.UserQuery;
import com.shanghai.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    /**
     * 用户数据查询
     *  多条件分页查询
     * @param userQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> queryUserByParams(UserQuery userQuery){
        return userService.queryUserByParams(userQuery);
    }



    /**
     * 添加用户
     * @param userModel
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(@RequestBody UserModel userModel){
        //调用Service层的添加方法
        userService.addUser(userModel);
        return success("用户添加成功！");
    }


    /**
     * 更新用户
     * @param userModel
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(@RequestBody UserModel userModel){
        //调用Service层的添加方法
        userService.updateUser(userModel);
        return success("用户更新成功！");
    }



    /**
     * 进入添加/修改用户数据页面
     * @return
     */
    @RequestMapping("toUserPage")
    public String toUserPage(Integer userId, HttpServletRequest request){

        //判断userId是否为空
        if(userId != null){
            //通过ID查询用户数据
            UserModel user = userService.selectUserByUserId(userId);
            //将数据设置到请求域中
            request.setAttribute("user", user);

        }

        return "user/user_add_update";
    }


    /**
     * 删除用户
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        //调用service层的删除方法
        userService.deleteUser(ids);
        return success("用户数据删除成功！");
    }




    /**
     * 进入用户管理页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "user/user_index";
    }

    /*----------------------------------------------------------------*/

    /**
     * 查询所有的角色列表
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(){
        return userService.queryAllRoles();
    }

}
