package com.pinyougou.page.service.impl;

import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/8$ 20:36$
 */
@Component
public class PageListener implements MessageListener {
    @Autowired
    private ItemPageService pageService;
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage=(TextMessage)message;
            String id = textMessage.getText();
            pageService.genItemHtml(Long.valueOf(id));
            System.out.println("成功生成静态页面");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
