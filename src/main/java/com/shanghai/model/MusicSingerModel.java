package com.shanghai.model;

import com.shanghai.base.BaseQuery;

public class MusicSingerModel extends BaseQuery {
    private Integer singerId;
    private String singerName;

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
}
