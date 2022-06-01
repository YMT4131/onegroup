package com.shanghai.service;

import com.shanghai.base.BaseService;
import com.shanghai.base.ResultInfo;
import com.shanghai.dao.UserMapper;
import com.shanghai.po.User;
import com.shanghai.po.vo.UserModel;
import com.shanghai.utils.AssertUtil;
import com.shanghai.utils.Md5Util;
import com.shanghai.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User,Integer> {
    @Autowired
    private UserMapper userMapper;

    public ResultInfo login(UserModel userModel){
        ResultInfo resultInfo = new ResultInfo();
        //校验参数是否为空
        checkLoginData(userModel);
        //校验登录信息与数据库的是否匹配
        userModel = checkUser(userModel);
        resultInfo.setResult(userModel);
        return resultInfo;
    }
    /**
     * 参数空值判断
     * @param userModel
     */
    private void checkLoginData(UserModel userModel){
        AssertUtil.isTrue(StringUtils.isBlank(userModel.getUserName()),"登录名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userModel.getUserPwd()), "密码不能为空");
    }

    /**
     * 将登录信息与数据库中的信息进行比对
     * @param userModel
     * @return
     */
    private UserModel checkUser(UserModel userModel){
        User user = userMapper.queryUserByName(userModel.getUserName());
        //判断当前用户是否在数据库中存在
        AssertUtil.isTrue(user == null, "用户名不存在");
        //判断密码是否匹配
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(userModel.getUserPwd()))
                , "密码不正确");
        userModel.setUserId(user.getUserId());
        //将id进行加密操作
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getUserId()));
        return userModel;
    }
}
