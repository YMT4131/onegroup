package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.query.ListQuery;
import com.shanghai.vo.List;

import java.util.Map;

public interface ListMapper extends BaseMapper<List,Integer> {
    public java.util.List<ListQuery> selectByParams(ListQuery query);

    public void saveList(ListQuery listQuery);

    public void updateList(ListQuery listQuery);

    public void deleteBatches(Integer[] listIds);
}