package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {
    UserRole queryUserRole(Integer userId);
    Integer deleteUserRole(Integer userId);
    Integer insertUserRole(UserRole userRole);
    public void updateUserRole(UserRole userRole);
}