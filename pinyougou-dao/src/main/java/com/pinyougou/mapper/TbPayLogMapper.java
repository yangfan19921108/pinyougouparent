package com.pinyougou.mapper;

import com.pinyougou.pojo.TbPayLog;
import com.pinyougou.pojo.TbPayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbPayLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    long countByExample(TbPayLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int deleteByExample(TbPayLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int deleteByPrimaryKey(String outTradeNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int insert(TbPayLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int insertSelective(TbPayLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    List<TbPayLog> selectByExample(TbPayLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    TbPayLog selectByPrimaryKey(String outTradeNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByExampleSelective(@Param("record") TbPayLog record, @Param("example") TbPayLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByExample(@Param("record") TbPayLog record, @Param("example") TbPayLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByPrimaryKeySelective(TbPayLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_pay_log
     *
     * @mbg.generated Tue Nov 20 19:21:23 CST 2018
     */
    int updateByPrimaryKey(TbPayLog record);
}