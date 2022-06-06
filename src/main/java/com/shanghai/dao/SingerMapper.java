package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.query.SingerQuery;
import com.shanghai.vo.Singer;

import java.util.List;

public interface SingerMapper extends BaseMapper<Singer,Integer> {
//多条件查询歌手列表
    List<Singer> querySingerByParams(SingerQuery singerQuery);

    //
}