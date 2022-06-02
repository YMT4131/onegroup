package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.LayerTableModel;
import com.shanghai.base.ResultInfo;
import com.shanghai.query.MusicQuery;
import com.shanghai.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@RequestMapping("musicManage")
@Controller
public class MusicController extends BaseController {
    @Resource
    private MusicService musicService;
    /**
     * 搜索按钮绑定-多条件查询
     * @param musicQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public LayerTableModel searchMusicByQuery(MusicQuery musicQuery){
        return musicService.searchMusicByQuery(musicQuery);
    }
    /**
     * 行工具栏删除绑定事件
     *      根据主键id删除单条记录
     * @param musicId
     * @return
     */
    @PostMapping("delOne")
    @ResponseBody
    public ResultInfo delOneMusic(Integer musicId){
        musicService.deleteOneMusic(musicId);
        return success("删除成功!");
    }

    /**
     * 头部工具栏绑定
     *      批量删除
     *          接受前端传递过来的数据
     * @param ids
     * @return
     */
    @PostMapping("delBatch")
    @ResponseBody
    public ResultInfo delBatch(Integer[] ids){
        musicService.delBatch(ids);
        return success("删除成功！");
    }

}

