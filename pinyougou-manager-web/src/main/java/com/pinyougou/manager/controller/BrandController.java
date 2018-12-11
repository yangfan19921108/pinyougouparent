package com.pinyougou.manager.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/11/20$ 14:34$
 * @Version: "10.0.1" 2018-04-17
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.finAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(Integer page,Integer rows){
        return  brandService.findPage(page,rows);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand){
        try {
            brandService.add(tbBrand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand, Integer page,Integer rows){
      /*  System.out.println("00000000000");*/
        PageResult pageResult = brandService.search(tbBrand, page, rows);
    /*    System.out.println("111");*/
        return pageResult;

    }
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }
   @RequestMapping ("selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }
}
