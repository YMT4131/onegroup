package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.model.TypeModel;
import com.shanghai.po.vo.Type;
import com.shanghai.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping("type")
public class TypeController extends BaseController {
    @Resource
    private TypeService typeService;


    @RequestMapping("index")
    public String index(){
        return "type/music_type";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryType(TypeModel typeModel){
        return typeService.queryType(typeModel);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addType(Type type){
        typeService.addType(type);
        return success("添加成功");
    }

    @RequestMapping("addTypePage")
    public String addTypePage(){

        return "type/add_type";
    }

}










