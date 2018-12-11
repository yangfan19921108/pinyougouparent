package com.pinyougou.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/3$ 14:29$
 */
@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {
    @Reference
    private ItemSearchService itemSearchService;
    @RequestMapping("/search")
    public Map<String,Object> search(@RequestBody Map searchMap){
        return itemSearchService.search(searchMap);
    }
}
