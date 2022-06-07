package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.model.TypeModel;
import com.shanghai.po.vo.Type;

import java.util.List;


public interface TypeMapper extends BaseMapper<Type,Integer> {

    List<TypeModel> queryType(TypeModel type);

    //根据名称查找数据
    Type queryTypeByName(String typeName);



}