package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {
    //通过角色ID删除权限记录
    void deleteByRoleId(Integer roleId);
    //通过角色ID查询权限记录
    Integer countPermissionByRoleId(Integer roleId);
    //查询角色拥有的所有资源ID的集合
    List<Integer> queryRoleHasModuleIdByRoleId(Integer roleId);
}