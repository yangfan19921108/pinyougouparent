package com.pinyougou.page.service.impl;

import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.page.service.ItemPageService;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemExample;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/6$ 13:57$
 */
@Service
public class ItemPageServiceImpl implements ItemPageService {
    @Value("${pagedir}")
    private String pagedir;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Autowired
    private TbGoodsDescMapper goodsDescMapper;
    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Autowired
    private TbItemMapper itemMapper;
    @Override
    public boolean genItemHtml(Long goodsId) {
        try {
            //加载模板
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            //创建数据模型
            Map dateModel=new HashMap<>();
            //1.加载商品表数据
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(goodsId);
            dateModel.put("goods",tbGoods);
            //2.加载商品扩展数据
            TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);
            dateModel.put("goodsDesc",tbGoodsDesc);
            //3.商品分类
            String itemCat1 = itemCatMapper.selectByPrimaryKey(tbGoods.getCategory1Id()).getName();
            String itemCat2 = itemCatMapper.selectByPrimaryKey(tbGoods.getCategory2Id()).getName();
            String itemCat3 = itemCatMapper.selectByPrimaryKey(tbGoods.getCategory3Id()).getName();
            dateModel.put("itemCat1",itemCat1);
            dateModel.put("itemCat2",itemCat2);
            dateModel.put("itemCat3",itemCat3);
            //4.SKUl列表
            TbItemExample example=new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo("1");//状态为有效
            criteria.andGoodsIdEqualTo(goodsId);//指定SPU ID
            example.setOrderByClause("is_default desc");//按照降序排列,保证第一个为默认
            List<TbItem> tbItemList = itemMapper.selectByExample(example);
            dateModel.put("itemList",tbItemList);
            //   Writer out=new FileWriter(pagedir+goodsId+".html");
            Writer out=new OutputStreamWriter(new FileOutputStream(pagedir+goodsId+".html"),"UTF-8");
            template.process(dateModel,out);
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteItemHtml(Long[] ids){
        try {
            for (Long id : ids) {
                new File(pagedir+id+".html").delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
