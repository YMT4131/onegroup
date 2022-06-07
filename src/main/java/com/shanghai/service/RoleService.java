package com.shanghai.service;

import com.shanghai.base.BaseService;
import com.shanghai.dao.RoleMapper;
import com.shanghai.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;
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
}
