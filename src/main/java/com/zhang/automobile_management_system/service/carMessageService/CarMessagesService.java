package com.zhang.automobile_management_system.service.carMessageService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarOrder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 主页功能实现
 * @throws
 * @date 2023/3/30 19:00
 */
@Repository
public interface CarMessagesService {
    //增
    int saveCarMessage(CarMessages carMessages);
    //删
    int deleteCarMessage(String id);
    //改
    int updateCarMessage(String id,CarMessages carMessages);
    //查
    PageInfo<CarMessages> getCarMessage(String  id);

    CarMessages getCarMessageById(String  id);

    PageInfo<CarMessages> getAllCarMessage(Integer index);

    PageInfo<CarMessages> selectLike(String keyword,Integer index);

}
