package com.pinyougou.mapper;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    long countByExample(TbGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int deleteByExample(TbGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int insert(TbGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int insertSelective(TbGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    List<TbGoods> selectByExample(TbGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    TbGoods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByExampleSelective(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByExample(@Param("record") TbGoods record, @Param("example") TbGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByPrimaryKeySelective(TbGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByPrimaryKey(TbGoods record);
}