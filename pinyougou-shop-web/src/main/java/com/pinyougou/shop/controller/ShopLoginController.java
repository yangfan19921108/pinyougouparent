package com.pinyougou.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/11/23$ 22:47$
 */
@RestController
@RequestMapping("/shopLogin")
public class ShopLoginController {

    @RequestMapping("name")
    public Map name(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap map = new HashMap();
        map.put("loginName",name);
        return map;
    }
}
