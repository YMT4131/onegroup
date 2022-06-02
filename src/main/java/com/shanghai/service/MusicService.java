package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.LayerTableModel;
import com.shanghai.dao.MusicMapper;
import com.shanghai.model.MusicModel;
import com.shanghai.po.Music;
import com.shanghai.query.MusicQuery;
import com.shanghai.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MusicService extends BaseService<Music,Integer> {
    @Resource
    private MusicMapper musicMapper;

    /**
     * 歌曲搜索-多条件查询
     *      并且开启分页
     * @param musicQuery
     * @return
     */
    public LayerTableModel searchMusicByQuery(MusicQuery musicQuery){
        LayerTableModel layerTableModel = new LayerTableModel();
        //开启分页
        PageHelper.startPage(musicQuery.getPage(),musicQuery.getLimit());
        //得到对应的分页对象
        PageInfo<MusicModel> pageInfo = new PageInfo<>(
                musicMapper.searchMusic(musicQuery));
        //分页对象设置到返回值中
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }

    /**
     *  执行删除操作-实际上是更改isValid的值，绑定的是行工具栏右侧的
     *  1.判断musicId非空
     *  2.调用dao层方法，判断受影响行数
     * @param musicId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOneMusic(Integer musicId){
        AssertUtil.isTrue(musicId == null ||
                musicMapper.selectByPrimaryKey(musicId) == null,"所删除的记录不存在！");
        AssertUtil.isTrue(musicMapper.deleteByPrimaryKey(musicId) != 1,"删除失败！");
    }
}
