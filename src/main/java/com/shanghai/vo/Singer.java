package com.shanghai.vo;

public class Singer {
    private Integer singerId;

    private String singerName;

    private String singerGender;

    private String singerNal;

    private String singerInfo;

    private Integer isJoin;

    private Integer isValid;

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
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getSingerGender() {
        return singerGender;
    }

    public void setSingerGender(String singerGender) {
        this.singerGender = singerGender == null ? null : singerGender.trim();
    }

    public String getSingerNal() {
        return singerNal;
    }

    public void setSingerNal(String singerNal) {
        this.singerNal = singerNal == null ? null : singerNal.trim();
    }

    public String getSingerInfo() {
        return singerInfo;
    }

    public void setSingerInfo(String singerInfo) {
        this.singerInfo = singerInfo == null ? null : singerInfo.trim();
    }

    public Integer getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(Integer isJoin) {
        this.isJoin = isJoin;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}