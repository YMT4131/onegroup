package com.shanghai.query;

import com.shanghai.base.BaseQuery;

public class MusicQuery extends BaseQuery {
    //分页参数继承得到的
    private String musicName;
    private String singerName;
    private String albumName;
    private String typeName;
    //是否会员可听
    private Integer isForVip;


    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
