package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.base.LayerTableModel;
import com.shanghai.dao.MusicMapper;
import com.shanghai.model.*;
import com.shanghai.po.Music;
import com.shanghai.query.MusicQuery;
import com.shanghai.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
     *  执行删除操作-实际上是更改isValid的值，绑定的是行工具栏右侧的删除
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

    /**
     * 编辑操作-歌曲行工具栏
     *    1.参数校验
     *    --musicId非空，且数据库中对应的记录非空！--主键不能改
     *    --musicName 非空
     *    --singerId 非空
     *    --albumId  非空
     *    --typeId1   非空
     *    --isForVip  非空
     *    2.默认参数
     *    --isValid   默认为1--有效
     *    3.调用dao层方法，判断受影响行数
     *    updateByPrimaryKeySelective
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMusic(Music music){
        //参数校验
        checkMusicEditParams(music);
        //设置默认值
        music.setIsValid(1);
        AssertUtil.isTrue(musicMapper.updateByPrimaryKeySelective(music) !=1,"编辑失败!");
    }
    //1.编辑操作参数校验
    private void checkMusicEditParams(Music music) {
        AssertUtil.isTrue(music.getMusicId() == null,"待更新记录不存在！");
        Music temp = musicMapper.selectByPrimaryKey(music.getMusicId());
        AssertUtil.isTrue(null == temp,"待更新记录不存在！");

        AssertUtil.isTrue(music.getMusicName() == null,"歌曲名不能为空！");
        AssertUtil.isTrue(music.getSingerId() == null,"歌手不能为空！");
        AssertUtil.isTrue(music.getAlbumId() == null,"专辑不能为空！");
        AssertUtil.isTrue(music.getTypeId1() == null,"歌曲类型不能为空！");
        AssertUtil.isTrue(music.getIsForVip() == null,"请标记是否为会员可听！");
    }


    /**
     * 执行批量删除操作-实际上是更改isValid的值，绑定的是头部工具栏的删除
     *   1.判断数组ids非空
     *   2.调用dao层方法，判断受影响行数
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delBatch(Integer[] ids){
        AssertUtil.isTrue(ids.length == 0 || null == ids ,"请选择需要删除的记录！");
        AssertUtil.isTrue(musicMapper.deleteBatch(ids) != ids.length,"删除失败!");

    }

    /**
     * 添加操作--头部工具栏添加按钮绑定
     *  1.参数校验
     *      musicId   --主键自增长
     *      musicName 非空 数据存在
     *      singerId  非空
     *      albumId   非空
     *      typeId1、 非空
     *      typeId2、typeId3可以为空
     *  2.参数默认值
     *      isForVip 默认为0
     *      isValid  默认为1
     *  3.执行dao层方法，判断受影响行数
     * @param music
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addMusic(Music music){
        //参数校验
        checkMusicParams(music);
        //设置参数默认值
        setParamsDefaultValue(music);
        //执行dao层方法
        AssertUtil.isTrue(musicMapper.insertSelective(music) != 1,"歌曲添加失败!");

    }
    //添加操作--2.设置默认值
    private void setParamsDefaultValue(Music music) {
        music.setIsValid(1);
    }

    //添加操作--1.参数校验
    private void checkMusicParams(Music music) {
        //musicName 不为空--
        AssertUtil.isTrue(music.getMusicName() == null,"歌曲名不能为空！");
        //singerId 非空
        AssertUtil.isTrue(music.getSingerId() == null,"歌手名不能为空！");
        //albumId   非空
        AssertUtil.isTrue(music.getAlbumId() == null,"专辑名不能为空!");
        //typeId1  非空
        AssertUtil.isTrue(music.getTypeId1() == null,"歌曲风格至少选择一种！");
    }

    /**
     * 添加操作
     *      歌手下拉框
     * @return
     */
    public LayerTableModel querySinger(){
        LayerTableModel layerTableModel = new LayerTableModel();
        //开启分页
        PageHelper.startPage(1,10);
        //得到对应的分页对象
        PageInfo<MusicSingerModel> pageInfo = new PageInfo<>(
                musicMapper.querySinger());
        //分页对象设置到返回值中
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }
    /**
     * 添加操作
     *      专辑下拉框
     * @return
     */
    public LayerTableModel queryAlbum(){
        LayerTableModel layerTableModel = new LayerTableModel();
        //开启分页
        PageHelper.startPage(1,10);
        //得到对应的分页对象
        PageInfo<MusicAlbumModel> pageInfo = new PageInfo<>(
                musicMapper.queryAlbum());
        //分页对象设置到返回值中
        layerTableModel.setCount(pageInfo.getTotal());
        layerTableModel.setData(pageInfo.getList());
        return layerTableModel;
    }


}
