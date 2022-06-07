package com.shanghai.query;

import com.shanghai.base.BaseQuery;

public class MusicQuery extends BaseQuery {
    //分页参数继承得到的
    //查询到前端给展示
    private String musicName;
    private String singerName;
    private String albumName;
    private String typeName;
    //是否会员可听
    private Integer isForVip;

    //为添加操作下拉框做准备
    private Integer musicId;
    private Integer singerId;
    private Integer albumId;
    private Integer typeId1;
    private Integer typeId2;
    private Integer typeId3;
    private Integer isValid;

    public Integer getIsForVip() {
        return isForVip;
    }

    public void setIsForVip(Integer isForVip) {
        this.isForVip = isForVip;
    }


    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getTypeId1() {
        return typeId1;
    }

    public void setTypeId1(Integer typeId1) {
        this.typeId1 = typeId1;
    }

    public Integer getTypeId2() {
        return typeId2;
    }

    public void setTypeId2(Integer typeId2) {
        this.typeId2 = typeId2;
    }

    public Integer getTypeId3() {
        return typeId3;
    }

    public void setTypeId3(Integer typeId3) {
        this.typeId3 = typeId3;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }




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
