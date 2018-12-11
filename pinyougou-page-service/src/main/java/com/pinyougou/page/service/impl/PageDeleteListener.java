package com.pinyougou.page.service.impl;

import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/8$ 20:55$
 */
@Component
public class PageDeleteListener implements MessageListener{
    @Autowired
    private ItemPageService itemPageService;
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage=(ObjectMessage)message;
            Long[] ids = (Long[]) objectMessage.getObject();
            boolean b = itemPageService.deleteItemHtml(ids);
            System.out.println("删除网页结果"+b);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
