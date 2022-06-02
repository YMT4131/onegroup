package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.LayerTableModel;
import com.shanghai.base.ResultInfo;
import com.shanghai.dao.AlbumMapper;
import com.shanghai.po.Album;
import com.shanghai.po.vo.AlbumModel;
import com.shanghai.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public void deleteAlbum(Integer[] ids) {
        AssertUtil.isTrue(ids.length<1, "请选择要删除的数据");
        AssertUtil.isTrue(albumMapper.deleteBatch(ids)<1, "删除失败，系统出错");
    }

    public LayerTableModel querySingerList(String singerName){
        LayerTableModel layerTableModel = new LayerTableModel();
        PageHelper.startPage(1, 10);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(albumMapper.querySingerList(singerName));
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }
    public AlbumModel queryAlbumModelById(Integer albumId){
        return albumMapper.queryAlbumModelById(albumId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateAlbum(Album album){
        //必填参数校验
        AssertUtil.isTrue(StringUtils.isBlank(album.getAlbumName()), "专辑名称不能为空");
        AssertUtil.isTrue(album.getAlbumReleaseDate()==null,"发行时间不能为空" );

        //默认字段赋值
        album.setIsValid(1);
        //专辑名称不能重复
        Album temp = albumMapper.queryAlbumByName(album.getAlbumName());
        //如果是更新
        if(album.getAlbumId()!=null){
            AssertUtil.isTrue(temp!=null && !temp.getAlbumId().equals(album.getAlbumId()), "专辑已存在");
            AssertUtil.isTrue(albumMapper.updateByPrimaryKeySelective(album)<1, "更新失败");
        }else{ //新增
            AssertUtil.isTrue(temp!=null, "专辑已存在");
            AssertUtil.isTrue(albumMapper.insertSelective(album)<1, "新增失败");
        }

    }
}
