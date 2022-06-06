package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.po.vo.AlbumModel;
import com.shanghai.query.SingerQuery;
import com.shanghai.service.SingerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer albumId, Model model){
        if(albumId != null){

        }
        return "album/album_add_update";
    }
}
