package com.shanghai.dao;

import com.shanghai.po.vo.MenuModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IndexMapper {
    //根据userId获取用户的角色,可能存在多角色
    List<Integer> queryRoleIdByUserId(Integer userId);
    //根据UserId查询菜单
    List<MenuModel> queryModuleByUserId(Integer UserId);
}
