package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import sun.misc.ASCIICaseInsensitiveComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/3$ 12:54$
 */
@Service(timeout = 3000)
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
  private SolrTemplate solrTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String, Object> search(Map searchMap) {
        Map<String, Object> map=new HashMap<>();
    /*    Query query =new SimpleQuery("*:*");
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        map.put("rows",tbItems.getContent());*/
        //查询列表
        map.putAll(searchList(searchMap));
        //2.根据关键字查询商品分类
        List<String> list = searchCategoryList(searchMap);
        map.put("categoryList",list);
        //3.查询品牌和规格列表
        String category = (String) searchMap.get("category");
        if(!"".equals(category)){//如果有分类
        map.putAll(searchBrandAndSpecList(category));
        }else {//如果没有分类名称，按照第一个
        if (list.size()>0){
            map.putAll(searchBrandAndSpecList(list.get(0)));
        }}
        return map;
    }

    /**
     * 导入数据
     * @param list
     */
    @Override
    public void importList(List list) {
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    @Override
    public void deleteByGoodsIds(List goodsIdList) {
        Query query =new SimpleQuery();
        Criteria criteria=new Criteria("item_goodsid").in(goodsIdList);
        query.addCriteria(criteria);
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

    /**
     * 查询列表
     * @param searchMap
     * @return
     */
    private Map searchList(Map searchMap){
        Map<String, Object> map=new HashMap<>();
        //1.高亮设置
        HighlightQuery query=new SimpleHighlightQuery();
        HighlightOptions highlightOptions=new HighlightOptions().addField("item_title");//设置高亮域
        highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀
        highlightOptions.setSimplePostfix("</em>");//高亮后缀
        query.setHighlightOptions(highlightOptions);//设置高亮选项
        //1.1关键字查询
        //关键字空格处理
        String keywords = (String) searchMap.get("keywords");
        searchMap.put("keywords",keywords.replace(" ",""));
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        //1.2按商品分类过滤
        if(!"".equals(searchMap.get("category"))){//如果用户选择了分类
            FilterQuery filterQuery = new SimpleFilterQuery();
            Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        //1.3按商品品牌过滤
        if(!"".equals(searchMap.get("brand"))){//如果用户选择了品牌
            FilterQuery filterQuery = new SimpleFilterQuery();
            Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        //1.4按商品规格过滤
        if(searchMap.get("spec")!=null){//如果用户选择了规格
            Map<String,String> specMap = (Map<String, String>) searchMap.get("spec");
            for (String key : specMap.keySet()) {
                FilterQuery filterQuery = new SimpleFilterQuery();
                Criteria filterCriteria = new Criteria("item_spec_"+key).is(searchMap.get(key));
                filterQuery.addCriteria(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }
        //1.5按价格进行筛选
        if(!"".equals(searchMap.get("price"))&&searchMap.get("price")!=null){
            String priceStr = (String) searchMap.get("price");
            String[] price = priceStr.split("-");
            if(!price[0].equals("0")){//r如果区间起点不是0
                Criteria filterCriteria = new Criteria("item_price").greaterThanEqual(price[0]);
                FilterQuery filterQuery=new SimpleFilterQuery(filterCriteria);
                query.addFilterQuery(filterQuery);
            }if (!price[1].equals("*")){//r如果区间起点不是*
                Criteria filterCriteria = new Criteria("item_price").lessThanEqual(price[1]);
                FilterQuery filterQuery=new SimpleFilterQuery(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }
        //1.6分页查询
        Integer pageNo= (Integer) searchMap.get("pageNo");//获取当前页码
        if(pageNo==null){
            pageNo=1;//默认第一页
        }
        Integer pageSize= (Integer) searchMap.get("pageSize");//获取每页显示条数
        if (pageSize==null){
            pageSize=20;//默认20
        }
        query.setOffset((pageNo-1)*pageSize);//开始索引
        query.setRows(pageSize);
        //1.7排序
        String sortValue = (String) searchMap.get("sort");//获取排序规则ASC/DESC
        String sortField = (String) searchMap.get("sortField");//获取排序字段
        if (sortValue!=null&&!sortValue.equals("")){
            if (sortValue.equals("ASC")){//升序
                Sort sort=new Sort(Sort.Direction.ASC,"item_"+sortField);
                query.addSort(sort);
            }
            if (sortValue.equals("DESC")){//升序
                Sort sort=new Sort(Sort.Direction.DESC,"item_"+sortField);
                query.addSort(sort);
            }

        }
        //***********  获取高亮结果集  ***********
        //高亮页对象
        HighlightPage<TbItem> highlightPage = solrTemplate.queryForHighlightPage(query, TbItem.class);
        for (HighlightEntry<TbItem> highlightEntry : highlightPage.getHighlighted()) {//循环高亮入口集合
            TbItem tbItem = highlightEntry.getEntity();//获得原实体类
            if (highlightEntry.getHighlights().size()>0&&highlightEntry.getHighlights().get(0).getSnipplets().size()>0){
                tbItem.setTitle(highlightEntry.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
            }
        }
        map.put("rows",highlightPage.getContent());
        map.put("totalPages",highlightPage.getTotalPages());//返回总页数
        map.put("total",highlightPage.getTotalElements());//返回总记录数
        return map;

    }

    /**
     * 查询分类列表
     * @param searchMap
     * @return
     */
    private List searchCategoryList(Map searchMap){
        List<String> list = new ArrayList<>();
        Query query=new SimpleQuery();
        //按照关键字查询
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        //设置分组选项
        GroupOptions groupOptions=new GroupOptions().addGroupByField("item_category");
        query.setGroupOptions(groupOptions);
        //得到分组页
        GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
        //根据列得到分组结果集
        GroupResult<TbItem> groupResult = page.getGroupResult("item_category");
        ////得到分组结果入口页
        Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
        //得到分组结果集合
        List<GroupEntry<TbItem>> content = groupEntries.getContent();
        for (GroupEntry<TbItem> entry : content) {
            list.add(entry.getGroupValue());//将分组结果的名称封装到返回值集合中
        }
        return  list;
    }
    private  Map searchBrandAndSpecList(String category){
        Map map = new HashMap<>();
        //获取模板id
        Long typeId = (Long) redisTemplate.boundHashOps("itemCat").get(category);
        if (typeId!=null){
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(typeId);
            List specList = (List) redisTemplate.boundHashOps("specList").get(typeId);
            map.put("brandList",brandList);
            map.put("sepcList",specList);
        }
        return map;
    }
}
