package com.shanghai.service;

import com.shanghai.base.BaseService;
import com.shanghai.base.ResultInfo;
import com.shanghai.dao.UserMapper;
import com.shanghai.po.User;
import com.shanghai.po.vo.UserModel;
import com.shanghai.po.vo.UserPasswordModel;
import com.shanghai.utils.AssertUtil;
import com.shanghai.utils.Md5Util;
import com.shanghai.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(UserPasswordModel model){
        //校验userID
        AssertUtil.isTrue(model.getUserId()==null,"未获取到当前用户信息");
        User user = userMapper.selectByPrimaryKey(model.getUserId());
        AssertUtil.isTrue(user==null,"当前用户信息获取错误");
        //校验旧密码是否正确
        AssertUtil.isTrue(StringUtils.isBlank(model.getOldPassword()),"旧密码不能为空");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(model.getOldPassword())), "旧密码输入不正确");
        //校验新密码
        AssertUtil.isTrue(StringUtils.isBlank(model.getNewPassword()),"新密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(model.getAgainPassword()),"新密码不能为空");
        AssertUtil.isTrue(!model.getNewPassword().equals(model.getAgainPassword()),"新密码两次输入不同");
        AssertUtil.isTrue(model.getOldPassword().equals(model.getNewPassword()),"新密码不能与旧密码相同");
        //提交到数据库
        user.setUserPwd(Md5Util.encode(model.getNewPassword()));
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1, "密码修改失败");
    }
}
