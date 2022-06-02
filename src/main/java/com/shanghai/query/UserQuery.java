package com.shanghai.query;

import com.shanghai.base.BaseQuery;

public class UserQuery extends BaseQuery {
    private Integer userId;     //序号
    private Integer isVip;      //是否会员
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
