package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.query.ListQuery;
import com.shanghai.vo.List;
import com.shanghai.vo.Type;

import java.util.Map;

public interface ListMapper extends BaseMapper<List,Integer> {
    public java.util.List<ListQuery> selectByParams(ListQuery query);

    public void saveList(ListQuery listQuery);

    public void updateList(ListQuery listQuery);

    public void deleteBatches(Integer[] listIds);

    public ListQuery selectByListId(Integer listId);

    public java.util.List<Map<String,Object>> queryAllTypes();

    public java.util.List<String> queryListHasMusicByListId(Integer listId);

    public java.util.List<Map<String,Object>> queryAllMusicByListId(Integer listId);

    public void deleteListHasMusicByListId(Integer listId);

    public void insertListHasMusic(Integer listId,Integer musicId);

    public Integer getMaxListId();

}