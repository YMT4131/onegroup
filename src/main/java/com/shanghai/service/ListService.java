package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.dao.ListMapper;
import com.shanghai.query.ListQuery;
import com.shanghai.vo.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ListService extends BaseService<List,Integer> {
    @Resource
    private ListMapper listMapper;

    /**
     * 分页查询歌单
     * @param query
     * @return
     */
    public Map<String,Object> selectByListName(ListQuery query){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<ListQuery> pageInfo =
                new PageInfo<ListQuery>(listMapper.selectByListName(query));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;

    }
}
