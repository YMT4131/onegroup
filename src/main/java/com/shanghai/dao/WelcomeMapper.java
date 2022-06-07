package com.shanghai.dao;

import com.shanghai.base.BaseMapper;
import com.shanghai.vo.Welcome;

public interface WelcomeMapper extends BaseMapper<Welcome,Integer> {
    public Integer countMusicNumber();

    public Integer countSingerNumber();

    public Integer countAlbumNumber();

    public Integer countListNumber();
}
