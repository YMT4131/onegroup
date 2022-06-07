package com.shanghai.service;

import com.shanghai.base.BaseService;
import com.shanghai.dao.ModuleMapper;
import com.shanghai.dao.PermissionMapper;
import com.shanghai.dao.RoleMapper;
import com.shanghai.po.Permission;
import com.shanghai.po.Role;
import com.shanghai.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    PermissionMapper permissionMapper;

    @Resource
    ModuleMapper moduleMapper;

        @Transactional(propagation = Propagation.REQUIRED)
        public void addRole(Role role){

            AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名称不能为空");

            Role temp =roleMapper.selectByRoleName(role.getRoleName());

            AssertUtil.isTrue(temp!= null,"角色名已存在，重新输入");

            role.setIsValid(1);
            role.setCreateDate(new Date());
            role.setUpdateDate(new Date());

            AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"角色添加失败");



        }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role){
            AssertUtil.isTrue(role.getRoleId() == null,"待更新记录不存在");
            Role temp = roleMapper.selectByPrimaryKey(role.getRoleId());
            AssertUtil.isTrue(null == temp,"待更新记录不存在");
            AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"用户名称不能为空");
            temp=roleMapper.selectByRoleName(role.getRoleName());
            AssertUtil.isTrue(temp!=null&&(!temp.getRoleId().equals(role.getRoleId())),"角色名已存在");
            role.setUpdateDate(new Date());
            AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"修改角色失败");

    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer[] ids) {
        //判断ID是否为空
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除记录不存在！");
        //执行删除(更新)操作，判断受影响的行数
        AssertUtil.isTrue(roleMapper.deleteBatch(ids) != ids.length, "用户删除失败！");

        }
     //将对应的角色ID与资源ID添加到对应的权限表
    //如果直接存可能会重复  如果有先删除 添加是批量添加
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoleGrant(Integer roleId, Integer[] ids) {
        //通过id查记录
        Integer count = permissionMapper.countPermissionByRoleId(roleId);

        if (count>0){
            permissionMapper.deleteByRoleId(roleId);
        }
        if (ids!=null&&ids.length>0){
            //定义permission集合
            List<Permission> permissionList = new ArrayList<>();

            //遍历资源ID数组
            for (Integer id:ids){
                Permission permission =new Permission();
                permission.setModuleId(id);
                permission.setRoleId(roleId);
                permission.setOptValue(moduleMapper.selectByPrimaryKey(id).getOptValue());
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                permissionList.add(permission);
            }

            //执行批量添加 判断受影响的行数
            AssertUtil.isTrue(permissionMapper.insertBatch(permissionList) != permissionList.size(),"角色授权失败");


        }

    }
}



