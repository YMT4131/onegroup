package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.LayerTableModel;
import com.shanghai.base.ResultInfo;
import com.shanghai.po.Album;
import com.shanghai.po.vo.AlbumModel;
import com.shanghai.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 专辑管理
 */
@Controller
@RequestMapping("album")
public class AlbumController extends BaseController {
    @Autowired
    private AlbumService albumService;

    /**
     * 跳转到专辑管理页面
     * @return
     */
    @RequestMapping("index")
    public String toAlbumIndex(){
        return "album/album_index";
    }

    /**
     * 跳转到新增或修改页面
     * @param albumId
     * @return
     */
    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer albumId, Model model){
        if(albumId != null){
            AlbumModel albumModel = albumService.queryAlbumModelById(albumId);
            model.addAttribute("albumModel",albumModel);
        }
        return "album/album_add_update";
    }

    /**
     * 专辑分页查询
     * @param albumModel
     * @return
     */
    @RequestMapping("queryAlbumPage")
    @ResponseBody
    public LayerTableModel queryAlbumPage(AlbumModel albumModel){
        return albumService.queryAlbumPage(albumModel);
    }

    /**
     * 删除专辑
     * @param ids
     * @return
     */
    @RequestMapping("deleteAlbum")
    @ResponseBody
    public ResultInfo deleteAlbum(@RequestBody Integer[] ids){
        albumService.deleteAlbum(ids);
        return new ResultInfo();
    }

    /**
     * 歌手下拉列表
     * @param singerName
     * @return
     */
    @RequestMapping("querySingerList")
    @ResponseBody
    public LayerTableModel querySingerList(String singerName){
        return albumService.querySingerList(singerName);
    }

    /**
     * 新增或修改
     * @param album
     * @return
     */
    @RequestMapping("saveOrUpdateAlbum")
    @ResponseBody
    public ResultInfo saveOrUpdateAlbum(@RequestBody Album album){
        albumService.saveOrUpdateAlbum(album);
        return new ResultInfo();
    }



}
