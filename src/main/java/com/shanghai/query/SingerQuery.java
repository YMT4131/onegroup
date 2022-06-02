package com.shanghai.query;

import com.shanghai.base.BaseQuery;
//歌手查询类
public class SingerQuery extends BaseQuery {
    private  String SingerName; //歌手名
    private  String SingerGender; //歌手性别
    private  String SingerNal; //歌手国籍

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getSingerGender() {
        return SingerGender;
    }

    public void setSingerGender(String singerGender) {
        SingerGender = singerGender;
    }

    public String getSingerNal() {
        return SingerNal;
    }

    public void setSingerNal(String singerNal) {
        SingerNal = singerNal;
    }
}
