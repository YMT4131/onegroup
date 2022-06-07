package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.Module;
import com.shanghai.po.vo.TreeModel;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module,Integer> {
    //查询所有资源列表
    public List<TreeModel> queryAllModules();

}