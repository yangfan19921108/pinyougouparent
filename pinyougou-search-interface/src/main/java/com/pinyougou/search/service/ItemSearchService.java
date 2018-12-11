package com.pinyougou.search.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 接口作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/3$ 11:51$
 * @Version: "10.0.1" 2018-04-17
 */
public interface ItemSearchService {
    public Map<String,Object> search(Map searchMap);

    /**
     * 导入数据
     * @param list
     */
    public void importList(List list);

    /**
     * 删除数据
     * @param goodsIdList
     */
    public void deleteByGoodsIds(List goodsIdList);
}
