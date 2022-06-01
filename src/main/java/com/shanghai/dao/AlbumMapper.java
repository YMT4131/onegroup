package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.po.Album;
import com.shanghai.po.vo.AlbumModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AlbumMapper extends BaseMapper<Album,Integer> {

    List<AlbumModel> queryAlbumList(AlbumModel albumModel);
    List<Map<String,Object>> querySingerList(String singerName);
    Album queryAlbumByName(String albumName);
    AlbumModel queryAlbumModelById(Integer albumId);

}