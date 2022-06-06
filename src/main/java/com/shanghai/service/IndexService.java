package com.shanghai.service;

import com.shanghai.dao.IndexMapper;
import com.shanghai.po.vo.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService{

    @Autowired
    private IndexMapper indexMapper;

    //菜单初始化
    public Map<String,Object> initMenu(Integer userId){
        //菜单初始化，数据结构参看init.json
        Map<String,Object> map = new HashMap<>();
        //homeInfo初始化
        Map<String,Object> homeInfo= new HashMap<>();
        homeInfo.put("title","首页");
        homeInfo.put("href","welcome");
        map.put("homeInfo",homeInfo);
        //logoInfo初始化
        Map<String,Object> logoInfo = new HashMap<>();
        logoInfo.put("title","音乐平台");
        logoInfo.put("image","images/logo.png");
        logoInfo.put("href","");
        map.put("logoInfo",logoInfo);
        //功能菜单初始化
        Map<String,Object> menuInfo1 = new HashMap<>();
        List<Map<String,Object>> menuInfo = new ArrayList<>();
        List<MenuModel>  childMenu = indexMapper.queryModuleByUserId(userId);


        menuInfo1.put("title", "常规管理");
        menuInfo1.put("icon", "fa fa-address-book");
        menuInfo1.put("href", "");
        menuInfo1.put("target", "_self");
        menuInfo1.put("child", childMenu);
        menuInfo.add(menuInfo1);

        map.put("menuInfo",menuInfo);
        return map;
    }
}
