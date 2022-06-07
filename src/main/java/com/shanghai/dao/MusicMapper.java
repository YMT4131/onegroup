package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.model.MusicAlbumModel;
import com.shanghai.model.MusicModel;
import com.shanghai.model.MusicSingerModel;
import com.shanghai.po.Music;
import com.shanghai.query.MusicQuery;

import java.util.List;

public interface MusicMapper extends BaseMapper<Music,Integer> {

    List<MusicModel> searchMusic(MusicQuery musicQuery);

    List<MusicSingerModel> querySinger();

    List<MusicAlbumModel> queryAlbum();



}