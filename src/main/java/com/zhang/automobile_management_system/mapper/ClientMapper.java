package com.zhang.automobile_management_system.mapper;

import com.zhang.automobile_management_system.bean.Client;
import com.zhang.automobile_management_system.bean.ClientExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper

public interface ClientMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int countByExample(ClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int deleteByExample(ClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int deleteByPrimaryKey(String clientId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int insert(Client record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int insertSelective(Client record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    List<Client> selectByExample(ClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    Client selectByPrimaryKey(String clientId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int updateByExampleSelective(@Param("record") Client record, @Param("example") ClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int updateByExample(@Param("record") Client record, @Param("example") ClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int updateByPrimaryKeySelective(Client record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbggenerated Tue Apr 11 23:23:02 CST 2023
     */
    int updateByPrimaryKey(Client record);
}