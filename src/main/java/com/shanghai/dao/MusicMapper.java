package com.shanghai.dao;

import com.shanghai.vo.Music;

public interface MusicMapper {
    int deleteByPrimaryKey(Integer musicId);

    int insert(Music record);

    int insertSelective(Music record);

    Music selectByPrimaryKey(Integer musicId);

    int updateByPrimaryKeySelective(Music record);

    int updateByPrimaryKey(Music record);
}