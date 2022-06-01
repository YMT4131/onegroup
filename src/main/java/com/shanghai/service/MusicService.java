package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.LayerTableModel;
import com.shanghai.dao.MusicMapper;
import com.shanghai.model.MusicModel;
import com.shanghai.po.Music;
import com.shanghai.query.MusicQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MusicService extends BaseService<Music,Integer> {
    @Resource
    private MusicMapper musicMapper;

    /**
     * 歌曲搜索-多条件查询
     *      并且开启分页
     * @param musicQuery
     * @return
     */
    public LayerTableModel searchMusicByQuery(MusicQuery musicQuery){
        LayerTableModel layerTableModel = new LayerTableModel();
        //开启分页
        PageHelper.startPage(musicQuery.getPage(),musicQuery.getLimit());
        //得到对应的分页对象
        PageInfo<MusicModel> pageInfo = new PageInfo<>(
                musicMapper.searchMusic(musicQuery));

        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());

        return layerTableModel;
    }
}
