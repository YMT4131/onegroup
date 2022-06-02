package com.shanghai.po.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shanghai.base.BaseQuery;

import java.math.BigDecimal;
import java.util.Date;

public class AlbumModel extends BaseQuery {
    private Integer albumId;

    private String albumName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date albumReleaseDate;

    private Integer singerId;

    private String singer;

    private BigDecimal albumPrice;

    private Integer isValid;

    private BigDecimal lowPrice;

    private BigDecimal higPrice;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(Date albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public BigDecimal getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(BigDecimal albumPrice) {
        this.albumPrice = albumPrice;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHigPrice() {
        return higPrice;
    }

    public void setHigPrice(BigDecimal higPrice) {
        this.higPrice = higPrice;
    }
}
