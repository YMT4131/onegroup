package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.dao.ListMapper;
import com.shanghai.query.ListQuery;
import com.shanghai.vo.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public Map<String,Object> selectByParams(ListQuery query){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<ListQuery> pageInfo =
                new PageInfo<ListQuery>(listMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;

    }

    /**
     * 新增歌单
     * @param listQuery
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveList(ListQuery listQuery){
        listMapper.saveList(listQuery);
    }

    /**
     * 更新歌单
     * @param listQuery
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateList(ListQuery listQuery){
        listMapper.updateList(listQuery);
    }

    public Map<String,Object> queryAllTypes(){
        Map<String,Object> map = new HashMap<>();
        return map;
    }

    /**
     * 批量删除歌单
     * @param listIds
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBatches(Integer[] listIds){
        //添加验证
        listMapper.deleteBatches(listIds);
    }
}
