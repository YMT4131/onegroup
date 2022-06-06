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
    public String addTypePage(Integer id,Model model){
        if(id!=null){
            Type type = typeService.selectByPrimaryKey(id);
            model.addAttribute("type",type);
        }
        return "type/add_type";
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateType(Type type){
        typeService.updateType(type);
        return success("修改成功");
    }

    //批量删除
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteType(Integer[] ids) {
        typeService.deleteType(ids);
        return success("音乐类型删除成功！");
    }

}










