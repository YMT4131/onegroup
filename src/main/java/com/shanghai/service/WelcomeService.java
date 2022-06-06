package com.shanghai.service;


import com.shanghai.base.BaseService;
import com.shanghai.dao.WelcomeMapper;
import com.shanghai.vo.Welcome;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WelcomeService extends BaseService<Welcome,Integer> {

    @Resource
    private WelcomeMapper welcomeMapper;

    public Welcome welcomeInfo(){
        Welcome welcome=new Welcome();
        welcome.setMusicNumber(welcomeMapper.countMusicNumber());
        welcome.setSingerNumber(welcomeMapper.countSingerNumber());
        welcome.setAlbumNumber(welcomeMapper.countAlbumNumber());
        welcome.setListNumber(welcomeMapper.countListNumber());
        return welcome;
    }

}
