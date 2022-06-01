package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.LayerTableModel;
import com.shanghai.dao.AlbumMapper;
import com.shanghai.po.Album;
import com.shanghai.po.vo.AlbumModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService extends BaseService<Album,Integer> {
    @Autowired
    private AlbumMapper albumMapper;

    public LayerTableModel queryAlbumPage(AlbumModel albumModel){
        LayerTableModel layerTableModel = new LayerTableModel();
        PageHelper.startPage(albumModel.getPage(), albumModel.getLimit());
        PageInfo<AlbumModel> pageInfo = new PageInfo<>(albumMapper.queryAlbumList(albumModel));
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }
}
