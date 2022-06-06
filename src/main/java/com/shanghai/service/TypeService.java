package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.dao.TypeMapper;
import com.shanghai.model.TypeModel;

import com.shanghai.po.vo.Type;
import com.shanghai.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TypeService extends BaseService<Type,Integer> {
    @Resource
    private TypeMapper typeMapper;

    //分页查询
    public Map<String,Object> queryType(TypeModel typeModel){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(typeModel.getPage(), typeModel.getLimit());
        List<TypeModel> types = typeMapper.queryType(typeModel);
        PageInfo<TypeModel> pageInfo = new PageInfo<>(types);
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }
    //添加数据
    @Transactional(propagation = Propagation.REQUIRED)
    public void addType(Type type){
        //参数校验
        checkAddType(type.getTypeName(),type.getTypeInfo());

        AssertUtil.isTrue(typeMapper.queryTypeByName(type.getTypeName())!=null,"重名，请重新输入");
        //设置默认值
        type.setIsValid(1);
        //执行
        AssertUtil.isTrue(typeMapper.insertSelective(type)<1,"添加音乐类型失败");
    }

    //修改数据
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateType(Type type){
        //参数校验
        checkAddType(type.getTypeName(),type.getTypeInfo());
        //校验修改名称唯一
        Type type1 = typeMapper.queryTypeByName(type.getTypeName());
        AssertUtil.isTrue(type1!=null && !type1.getTypeId().equals(type.getTypeId()),"名称已存在");
    }

    //名称，简介
    private void checkAddType(String typeName, String typeInfo) {
        AssertUtil.isTrue(StringUtils.isBlank(typeName),"类型名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(typeInfo),"简介不能为空");

    }

}
