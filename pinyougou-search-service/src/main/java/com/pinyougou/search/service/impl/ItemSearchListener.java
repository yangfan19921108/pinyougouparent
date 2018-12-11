package com.pinyougou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/8$ 19:19$
 */
@Component
public class ItemSearchListener implements MessageListener {
    @Autowired
    private ItemSearchService itemSearchService;
    @Override
    public void onMessage(Message message) {
        System.out.println("监听到消息");
        try {
            TextMessage textMessage=(TextMessage)message;
            String text = textMessage.getText();
            List<TbItem> tbItems = JSON.parseArray(text, TbItem.class);
            for (TbItem tbItem : tbItems) {
                System.out.println(tbItem.getId()+" "+tbItem.getTitle());
                Map specMap = JSON.parseObject(tbItem.getSpec());//将 spec 字段中的 json字符串转换为 map
                tbItem.setSpecMap(specMap);//给带注解的字段赋值
            }
            itemSearchService.importList(tbItems);//导入
            System.out.println("成功导入索引库");
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
