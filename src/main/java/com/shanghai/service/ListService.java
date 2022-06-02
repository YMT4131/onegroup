package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.LayerTableModel;
import com.shanghai.dao.ListMapper;
import com.shanghai.query.ListQuery;
import com.shanghai.vo.List;
import com.shanghai.vo.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
        java.util.List<ListQuery> listQueries = listMapper.selectByParams(query);
        for(ListQuery listQuery:listQueries){
            String musicName = (listMapper.queryListHasMusicByListId(listQuery.getListId())).toString();
            musicName = musicName.substring(1,musicName.length()-1);
            listQuery.setMusicName(musicName);
        }
        PageInfo<ListQuery> pageInfo =
                new PageInfo<ListQuery>(listQueries);
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
    public void saveList(ListQuery listQuery,Integer[] musicId){
        listMapper.saveList(listQuery);
        updateListHasMusic(listMapper.getMaxListId(),musicId);
    }

    /**
     * 更新歌单
     * @param listQuery
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateList(ListQuery listQuery,Integer[] musicId){
        listMapper.updateList(listQuery);
        updateListHasMusic(listQuery.getListId(),musicId);
    }


    /**
     * 批量删除歌单
     * @param listIds
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBatches(Integer[] listIds){
        //添加验证
        listMapper.deleteBatches(listIds);
        for(Integer listId:listIds){
            listMapper.deleteListHasMusicByListId(listId);
        }
    }

    /**
     * 通过歌单序号查询歌单模型
     * @param listId
     * @return
     */
    public ListQuery selectByListId(Integer listId){
        return listMapper.selectByListId(listId);
    }

    /**
     * 查询所有音乐类型
     * @return
     */
    public LayerTableModel queryAllTypes(){
        LayerTableModel layerTableModel = new LayerTableModel();
        PageHelper.startPage(1, 10);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(listMapper.queryAllTypes());
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }


    /**
     * 查询音乐，歌单中的做标记
     * @return
     */
    public LayerTableModel queryAllMusicByListId(Integer listId){
        LayerTableModel layerTableModel = new LayerTableModel();
        PageHelper.startPage(1, 10);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(listMapper.queryAllMusicByListId(listId));
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }

    /**
     * 更新歌单歌曲关系表
     * @param listId
     * @param musicIds
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateListHasMusic(Integer listId,Integer[] musicIds){
        listMapper.deleteListHasMusicByListId(listId);
        for(Integer musicId:musicIds){
            listMapper.insertListHasMusic(listId,musicId);
        }
    }

}
