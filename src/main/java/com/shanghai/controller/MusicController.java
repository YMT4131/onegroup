package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.service.MusicService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class MusicController extends BaseController {
    @Resource
    private MusicService musicService;
}
