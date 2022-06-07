package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.vo.AlbumModel;
import com.shanghai.query.ListQuery;
import com.shanghai.query.SingerQuery;
import com.shanghai.service.SingerService;
import com.shanghai.vo.Singer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("singer")
public class SingerController extends BaseController {

    @Resource
    private SingerService singerService;
    //多条件查询歌手
    @RequestMapping("singerlist")
    @ResponseBody
    public Map<String,Object> queryByParams(SingerQuery singerQuery){
        return singerService.queryByParams(singerQuery);
    }
    //进入歌手管理首页
    @RequestMapping("index")
    public  String toSingerPage(){

        return "singer/singer_index";
    }

    //歌手数据添加与歌手表单页面视图转发
    @RequestMapping("addOrUpdateSinger")
    public String addOrUpdateSingerPage(Integer singerId, Model model){
        if(singerId != null){
            model.addAttribute("singer",singerService.selectByPrimaryKey(singerId));
        }
        return "singer/singer_add_update";
    }
    //添加歌手
    @RequestMapping("addsinger")
    @ResponseBody
    public ResultInfo addSinger(Singer singer){
        singerService.saveSinger(singer);
        return success("歌手添加成功");
    }

    @RequestMapping("updatesinger")
    @ResponseBody
    public ResultInfo updateSinger(Singer singer){
        singerService.updateSinger(singer);
        return success("歌手更新成功");
    }
}
