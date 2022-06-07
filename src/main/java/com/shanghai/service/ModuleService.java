package com.shanghai.service;

import com.shanghai.base.BaseService;
import com.shanghai.dao.ModuleMapper;
import com.shanghai.dao.PermissionMapper;
import com.shanghai.po.Module;
import com.shanghai.po.vo.TreeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    //查询所有资源列表
    public List<TreeModel> queryAllModules(Integer roleId){
        //查询所有的资源列表
        List<TreeModel> treeModelList =moduleMapper.queryAllModules();
        //查询指定角色已经授权过的资源列表
        List<Integer> permissionIds = permissionMapper.queryRoleHasModuleIdByRoleId(roleId);
        //判断角色是否用有资源ID
        if (permissionIds !=null && permissionIds.size()>0){
            //循环所有的资源列表 判断是否有与用户资源Id所匹配的
            treeModelList.forEach(treeModel -> {
                //判断角色的资源id是否有当前遍历的资源的ID
                if (permissionIds.contains(treeModel.getId())){
                    treeModel.setChecked(true);
                }
            });
        }
        return treeModelList;
    }
}
