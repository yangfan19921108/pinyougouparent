package com.yangfan.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 消息监听类
 * @Author: yf_mood
 * @CreateDate: 2018/12/9$ 17:53$
 */
@Component
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;
    @JmsListener(destination = "sms")
    public void sendSms(Map<String,String> map){
        //String mobile, String template_code, String
        //            sign_name, String param
        try {
            SendSmsResponse response = smsUtil.sendSms(map.get("mobile"), map.get("template_code"), map.get("sign_name"), map.get("param"));
            System.out.println("Code:"+response.getCode());
            System.out.println("Message"+response.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
