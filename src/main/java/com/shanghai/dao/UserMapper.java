package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

    public User queryUserByName(String userName);

    /**
     * 多条件查询的接口不需要单独定义
     * 由于多个模块涉及到多条件查询操作，所以将对应的多条件查询功能定义在父接口BaseMapper
     */

}