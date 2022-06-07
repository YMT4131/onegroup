package com.shanghai.query;

import com.shanghai.base.BaseQuery;
//歌手查询类
public class SingerQuery extends BaseQuery {
    private Integer singerId;//歌手ID
    private String singerName; //歌手名
    private String singerGender; //歌手性别
    private String singerNal; //歌手国籍

    private String singerInfo;//歌手信息

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerGender() {
        return singerGender;
    }

    public void setSingerGender(String singerGender) {
        this.singerGender = singerGender;
    }

    public String getSingerNal() {
        return singerNal;
    }

    public void setSingerNal(String singerNal) {
        this.singerNal = singerNal;
    }

    public String getSingerInfo() {
        return singerInfo;
    }

    public void setSingerInfo(String singerInfo) {
        this.singerInfo = singerInfo;
    }
}