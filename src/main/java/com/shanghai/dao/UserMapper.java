package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.User;
import com.shanghai.po.vo.UserModel;
import com.shanghai.query.UserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

    public User queryUserByName(String userName);

    /**
     * 多条件查询的接口不需要单独定义
     * 由于多个模块涉及到多条件查询操作，所以将对应的多条件查询功能定义在父接口BaseMapper
     */

    //查询所有角色列表
    public List<Map<String, Object>> queryAllRoles();

    public UserModel selectUserByUserId(Integer userId);

    public Integer updateByUserModel(UserModel userModel);

    public List<UserModel> selectByParams(UserQuery userQuery);
}