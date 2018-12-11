package com.pinyougou.search.service.impl;

import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description:监听：用于删除索引库中记录
 * @Author: yf_mood
 * @CreateDate: 2018/12/8$ 19:58$
 */
@Component
public class ItemDeleteListener implements MessageListener {
    @Autowired
    private ItemSearchService itemSearchService;
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage=(ObjectMessage)message;
            Long[] ids = (Long[]) objectMessage.getObject();
            itemSearchService.deleteByGoodsIds(Arrays.asList(ids));
            System.out.println("成功删除索引库中的记录");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
