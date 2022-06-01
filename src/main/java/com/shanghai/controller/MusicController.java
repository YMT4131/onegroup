package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.LayerTableModel;
import com.shanghai.query.MusicQuery;
import com.shanghai.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@RequestMapping("musicManage")
@Controller
public class MusicController extends BaseController {
    @Resource
    private MusicService musicService;

    @RequestMapping("list")
    @ResponseBody
    public LayerTableModel searchMusicByQuery(MusicQuery musicQuery){
        return musicService.searchMusicByQuery(musicQuery);
    }

}
