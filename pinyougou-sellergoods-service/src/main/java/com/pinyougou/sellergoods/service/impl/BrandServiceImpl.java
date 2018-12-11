package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood   启动了之后 没有启动zookeeper 那怎么启动啊
 * ./zkServer.sh start  这个指令知道吧 嗯把他启动了
 * @CreateDate: 2018/11/20$ 14:21$
 * @Version: "10.0.1" 2018-04-17
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> finAll() {
      /*  try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Page<TbBrand> pages = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new PageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult search(TbBrand tbBrand, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (tbBrand!=null){
            if(tbBrand.getName()!=null&&tbBrand.getName().length()>0){
                    criteria.andNameLike("%"+tbBrand.getName().trim()+"%");
            }
            if (tbBrand.getFirstChar()!=null&& tbBrand.getFirstChar().length()>0){
                criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
            }
        }
       Page<TbBrand> pages = (Page<TbBrand>) tbBrandMapper.selectByExample(example);
        return new PageResult(pages.getTotal(),pages.getResult());
    }

    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }


}
