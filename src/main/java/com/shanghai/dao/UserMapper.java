package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByName(String userName);

}