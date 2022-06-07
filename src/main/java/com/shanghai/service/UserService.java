package com.shanghai.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.ResultInfo;
import com.shanghai.dao.UserMapper;
import com.shanghai.po.User;
import com.shanghai.po.vo.UserModel;
import com.shanghai.query.UserQuery;
import com.shanghai.po.vo.UserPasswordModel;
import com.shanghai.utils.AssertUtil;
import com.shanghai.utils.Md5Util;
import com.shanghai.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService<User,Integer> {
    @Autowired
    private UserMapper userMapper;

    public ResultInfo login(UserModel userModel) {
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
     *
     * @param userModel
     */
    private void checkLoginData(UserModel userModel) {
        AssertUtil.isTrue(StringUtils.isBlank(userModel.getUserName()), "登录名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userModel.getUserPwd()), "密码不能为空");
    }

    /**
     * 将登录信息与数据库中的信息进行比对
     *
     * @param userModel
     * @return
     */
    private UserModel checkUser(UserModel userModel) {
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



    /*---------------------------------------------------------*/


    /**
     * 多条件分页查询用户(返回的数据格式必须满足LayUi中数据表格要求的格式)
     * @param userQuery
     * @return
     */
    public Map<String, Object> queryUserByParams(UserQuery userQuery){
        Map<String, Object> map = new HashMap<>();

        //开启分页
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());

        //得到对应的分页对象
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(userQuery));

        //设置map对象
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        //设置分页好的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    /*---------------------------------------------------------*/

    /**
     * 添加用户
     *  1.参数校验
     *      userId      序号       非空
     *      isVip       是否会员    非空
     *      userName    昵称       非空
     *  2.设置相关字段的默认值
     *  3.执行添加操作，判断受影响的行数
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user){
        /* 1.参数校验 */
        checkUserParams(user.getUserId(), user.getIsVip(), user.getUserName());

        /* 2.设置相关字段的默认值 */
        //是否会员默认为非会员
        user.setIsVip(0);
        //注册时间默认是系统当前时间
        user.setUserJoinDate(new Date());

        /* 3.执行添加操作，判断受影响的行数 */

        AssertUtil.isTrue(userMapper.queryUserByName(user.getUserName()) != null, "该用户名已存在！");

        AssertUtil.isTrue(userMapper.insertSelective(user) != 1, "添加用户失败！");


        /*--*/


    }


    /**
     * 参数校验
     * @param userName
     */
    private void checkUserParams(Integer userId, Integer isVip, String userName) {
        //userName    昵称       非空
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户昵称不能为空！");
    }

    /*---------------------------------------------------------*/


    /**
     * 更新用户
     *  1.参数校验
     *      userId      序号       非空
     *      isVip       是否会员    非空
     *      userName    昵称       非空
     *  2.设置相关字段的默认值
     *  3.执行添加操作，判断受影响的行数
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user){
        /* 1.参数校验 */
        //AssertUtil.isTrue(null == user.getUserName(), "待更新记录不存在！");
        //用户ID  非空  数据库中对应的记录存在
        AssertUtil.isTrue(null == user.getUserId(), "待更新记录不存在！");
        //通过主键查询对象
        User temp = userMapper.selectByPrimaryKey(user.getUserId());
        //判断数据库中对应的记录是否存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");

        checkUserParams(user.getUserId(), user.getIsVip(), user.getUserName());


        User user1 = userMapper.queryUserByName(user.getUserName());
        AssertUtil.isTrue(user1 != null &&
                (!user1.getUserId().equals(user.getUserId())),"该用户已存在！");


        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) != 1, "用户更新失败！");
    }

    /*---------------------------------------------------------*/

    /**
     * 删除用户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer[] ids){
        //判断ID是否为空
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除记录不存在！");
        //执行删除(更新)操作，判断受影响的行数
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length, "用户删除失败！");
    }


}