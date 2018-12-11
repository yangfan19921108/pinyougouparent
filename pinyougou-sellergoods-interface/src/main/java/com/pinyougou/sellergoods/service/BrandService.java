package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;


/**
 * @Description: 接口作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/11/20$ 14:17$
 * @Version: "10.0.1" 2018-04-17
 */
public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    public List<TbBrand> finAll();

    /**
     * 分页查询
     * @param page 当前页码
     * @param size  每页显示记录数
     * @return
     */
    PageResult findPage(Integer page, Integer size);

    /**
     * 新增
     * @param tbBrand
     */
    void add(TbBrand tbBrand);

    /**
     * 修改
     * @param tbBrand
     */
    void update(TbBrand tbBrand);

    /**
     * 根据id批量删除
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 条件查询
     * @param tbBrand   查询条件
     * @param page      查询后结果的页码
     * @param size      查询后结果每页显示的页数
     * @return
     */
    PageResult search(TbBrand tbBrand, Integer page, Integer size);

    TbBrand findOne(Long id);

    List<Map> selectOptionList();
}
