package com.zhang.automobile_management_system.mapper;

import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarMessagesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CarMessagesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int countByExample(CarMessagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int deleteByExample(CarMessagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int deleteByPrimaryKey(String carId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int insert(CarMessages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int insertSelective(CarMessages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    List<CarMessages> selectByExample(CarMessagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    CarMessages selectByPrimaryKey(String carId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int updateByExampleSelective(@Param("record") CarMessages record, @Param("example") CarMessagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int updateByExample(@Param("record") CarMessages record, @Param("example") CarMessagesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int updateByPrimaryKeySelective(CarMessages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table car_messages
     *
     * @mbggenerated Thu Apr 20 13:40:16 CST 2023
     */
    int updateByPrimaryKey(CarMessages record);
}