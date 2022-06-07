package com.shanghai.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanghai.base.BaseService;
import com.shanghai.dao.SingerMapper;
import com.shanghai.query.ListQuery;
import com.shanghai.query.SingerQuery;
import com.shanghai.utils.AssertUtil;
import com.shanghai.vo.Singer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SingerService extends BaseService<Singer,Integer> {

    @Resource
    private SingerMapper singerMapper;
    //根据条件查询歌手
    public Map<String,Object> queryByParams(SingerQuery singerQuery) {
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        //开启分页
        PageHelper.startPage(singerQuery.getPage(),singerQuery.getLimit());
        //得到歌手列表
        List<Singer> list = singerMapper.querySingerByParams(singerQuery);
        //得到当前分页对象
        PageInfo<Singer> pageInfo = new PageInfo<>(list);
        //总记录数
        map.put("count",pageInfo.getTotal());
        //当前页的列表
        map.put("data",pageInfo.getList());

        return map;
    }

    //添加歌手
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSinger(Singer singer){
        //非空判断
        checkSingerParams(singer.getSingerName(),singer.getSingerGender(),singer.getSingerNal());

        if(StringUtils.isBlank(singer.getSingerInfo())){
            singer.setSingerInfo(null);
        }
        //设置默认值
        singer.setIsValid(1);
        singer.setIsJoin(1);
        //执行添加操作,判断受到影响的行数
        AssertUtil.isTrue(singerMapper.insertSelective(singer)<1,"歌手添加失败");
    }
    //判断信息是否为空
    private void checkSingerParams(String singerName, String singerGender, String singerNal) {
        AssertUtil.isTrue(StringUtils.isBlank(singerName),"歌手名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(singerGender),"歌手性别不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(singerNal),"歌手国籍不能为空！");

    }
    //更新歌手
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSinger(Singer singer){
        //Id 主键 非空，id 对应的数据存在
        AssertUtil.isTrue(null == singer.getSingerId(),"歌手记录不存在！");
        Singer temp = singerMapper.selectByPrimaryKey(singer.getSingerId());
        AssertUtil.isTrue(null == temp,"歌手记录不存在");
        //非空校验
        checkSingerParams(singer.getSingerName(),singer.getSingerGender(),singer.getSingerNal());

        //判断原来的值是否为空
        //执行更新
        AssertUtil.isTrue(singerMapper.updateByPrimaryKeySelective(singer) < 1,"更新歌手失败");

    }

    //删除歌手
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSinger(Integer[] ids) {
        //非空判断
        AssertUtil.isTrue(null == ids || ids.length < 1,"歌手记录不存在");
        //删除操作
        AssertUtil.isTrue(singerMapper.deleteBatch(ids) != ids.length,"歌手记录删除失败");
    }
}
