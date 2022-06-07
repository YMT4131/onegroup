package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<Role,Integer> {
    public Role selectByRoleName(String roleName);

}