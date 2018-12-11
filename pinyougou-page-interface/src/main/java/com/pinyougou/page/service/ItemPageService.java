package com.pinyougou.page.service;

/**
 * @Description: 商品详细页接口
 * @Author: yf_mood
 * @CreateDate: 2018/12/6$ 13:44$
 * @Version: "10.0.1" 2018-04-17
 */

public interface ItemPageService {
    /**
     * 生成商品详细页
     * @param goodsId
     * @return
     */
    public boolean genItemHtml(Long goodsId);

    public boolean deleteItemHtml(Long[] goodsIds);
}
