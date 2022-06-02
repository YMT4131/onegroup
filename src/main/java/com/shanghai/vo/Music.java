package com.shanghai.vo;

public class Music {
    private Integer musicId;

    private String musicName;

    private Integer singerId;

    private Integer albumId;

    private Integer typeId1;

    private Integer typeId2;

    private Integer typeId3;

    private Integer isForVip;

    private Integer isValid;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName == null ? null : musicName.trim();
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

    public Integer getIsForVip() {
        return isForVip;
    }

    public void setIsForVip(Integer isForVip) {
        this.isForVip = isForVip;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}