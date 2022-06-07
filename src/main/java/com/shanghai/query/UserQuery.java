package com.shanghai.query;

import com.shanghai.base.BaseQuery;

/**
 * 用户查询类
 *  为每一个功能去创建它们对应的一个query对象
 *  这样我们做查询就有一个对应的查询类
 */
public class UserQuery extends BaseQuery {

    // 分页参数

    //条件参数
    private Integer userId;     //序号
    private Integer isVip;      //是否是会员
    private String userName;    //昵称

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
