package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.query.ListQuery;
import com.shanghai.service.ListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("list")
public class ListController extends BaseController {
    @Resource
    private ListService listService;

    /**
     * 歌单主页面
     * @return
     */
    @RequestMapping("index")
    public String index () {
        return "/list/list_index";
    }

    /**
     * 歌单分页查询
     * @param listQuery
     * @return
     */

    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> selectByListName(ListQuery listQuery){
        return listService.selectByListName(listQuery);
    }

}
