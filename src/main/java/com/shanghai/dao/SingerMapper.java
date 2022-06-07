package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.query.SingerQuery;
import com.shanghai.vo.Singer;

import java.util.List;
import java.util.Map;

public interface SingerMapper extends BaseMapper<Singer,Integer> {
//多条件查询歌手列表
    List<Singer> querySingerByParams(SingerQuery singerQuery);

    public void saveSinger(SingerQuery singerQuery);

    public void updateByPrimaryKey(SingerQuery singer);

    public void updateSinger(SingerQuery singerQuery);

    public void deleteBatches(Integer[] SingerIds);

    public SingerQuery selectBySingerId(Integer singerId);

    public java.util.List<Map<String,Object>> queryAllTypes();

    public java.util.List<String> queryListHasMusicBySingerId(Integer singerId);

    public java.util.List<Map<String,Object>> queryAllMusicBySingerId(Integer singerId);

    public void deleteSingerBySingerId(Integer singerId);

    public void insertListHasMusic(Integer listId,Integer musicId);

    public Integer getMaxSingerId();
}