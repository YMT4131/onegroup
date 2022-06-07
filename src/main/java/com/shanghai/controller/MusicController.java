package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.LayerTableModel;
import com.shanghai.base.ResultInfo;
import com.shanghai.model.TypeModel;
import com.shanghai.po.Music;
import com.shanghai.query.MusicQuery;
import com.shanghai.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("musicManage")
@Controller
public class MusicController extends BaseController {
    @Resource
    private MusicService musicService;



    /**
     * 进入歌曲管理界面
     * @return
     */
    @RequestMapping("toSongManagePage")
    public String toSongManagePage(){
        return "musicManager/songManage";
    }
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

    /**
     * 歌曲编辑操作
     * @param music
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public ResultInfo updateMusic(Music music){
        musicService.updateMusic(music);
        return success("歌曲编辑成功！");
    }


    /**
     * 歌曲添加操作
     * 调用service层方法
     * @param
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addMusic(Music music){
        //添加操作
        musicService.addMusic(music);
        return success("歌曲添加成功！");
    }

    /**
     * 歌手选择的下拉框
     *
     * @return
     */
    @RequestMapping("singer")
    @ResponseBody
    public LayerTableModel querySinger(){
        return musicService.querySinger();
    }
    /**
     * 专辑选择的下拉框
     * @return
     */
    @RequestMapping("album")
    @ResponseBody
    public LayerTableModel queryAlbum(){
        return musicService.queryAlbum();
    }


    /**
     * 跳转到歌曲添加-编辑页面
     * @return
     */
    @RequestMapping("toAddOrUpdateMusicPage")
    public String toAddMusicPage(Integer musicId,HttpServletRequest request){
        if(musicId != null){
            Music m = musicService.selectByPrimaryKey(musicId);
            request.setAttribute("music",m);
        }
        return "musicManager/add_update_music";
    }
}

