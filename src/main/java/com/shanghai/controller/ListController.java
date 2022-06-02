package com.shanghai.controller;

import com.shanghai.base.BaseController;
import com.shanghai.base.ResultInfo;
import com.shanghai.query.ListQuery;
import com.shanghai.service.ListService;
import com.shanghai.utils.AssertUtil;
import com.shanghai.vo.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
            List list = listService.selectByPrimaryKey(listId);
            AssertUtil.isTrue(null==list,"数据异常，请重试");
            request.setAttribute("list",list);
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
    public ResultInfo saveList(ListQuery listQuery){
        listService.saveList(listQuery);
        return success("歌单添加成功");
    }


    /**
     * 添加歌单
     * @param listQuery
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateList(ListQuery listQuery){
        listService.updateList(listQuery);
        return success("歌单更新成功");
    }

//    @RequestMapping("queryAllTypes")
//    @ResponseBody
//    public List<Type> queryAllTypes(){
//        return listService.queryAllTypes();
//    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteList(Integer[] listIds){
        AssertUtil.isTrue(null==listIds || listIds.length == 0,"请选择待删除的营销机会记录");
        listService.deleteBatches(listIds);
        return success("歌单删除成功");
    }

}
