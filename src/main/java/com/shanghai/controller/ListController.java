package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.LayerTableModel;
import com.shanghai.base.ResultInfo;
import com.shanghai.query.ListQuery;
import com.shanghai.service.ListService;
import com.shanghai.utils.AssertUtil;
import com.shanghai.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public Map<String,Object> selectByParams(ListQuery listQuery){
        return listService.selectByParams(listQuery);
    }

    /**
     * 新增或更新歌单
     * @param listId
     * @param request
     * @return
     */
    @RequestMapping("toAddOrUpdate")
    public String toAddOrUpdate(Integer listId, HttpServletRequest request){
        if(null!=listId){
            ListQuery listQuery = listService.selectByListId(listId);
            AssertUtil.isTrue(null==listQuery,"数据异常，请重试");
            listService.setMusicNameForListQuery(listQuery);
            request.setAttribute("listQuery",listQuery);
        }
        return "/list/list_add_update";
    }

    /**
     * 新增歌单
     * @param listQuery
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveList(ListQuery listQuery,Integer[] musicId){
        listService.saveList(listQuery,musicId);
        return success("歌单添加成功");
    }

    /**
     * 添加歌单
     * @param listQuery
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateList(ListQuery listQuery,Integer[] musicId){
        listService.updateList(listQuery,musicId);
        return success("歌单更新成功");
    }

    @RequestMapping("queryAllTypes")
    @ResponseBody
    public LayerTableModel queryAllTypes(){
        return listService.queryAllTypes();
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteList(Integer[] listIds){
        AssertUtil.isTrue(null==listIds || listIds.length == 0,"请选择待删除的歌单");
        listService.deleteBatches(listIds);
        return success("歌单删除成功");
    }

    @RequestMapping("queryAllMusicByListId")
    @ResponseBody
    public LayerTableModel queryAllMusicByListId(Integer listId){
        return listService.queryAllMusicByListId(listId);
    }

}
