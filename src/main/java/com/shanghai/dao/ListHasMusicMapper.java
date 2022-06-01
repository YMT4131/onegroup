package com.shanghai.dao;

import com.shanghai.vo.ListHasMusic;

public interface ListHasMusicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ListHasMusic record);

    int insertSelective(ListHasMusic record);

    ListHasMusic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ListHasMusic record);

    int updateByPrimaryKey(ListHasMusic record);
}