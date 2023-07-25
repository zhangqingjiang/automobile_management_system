package com.zhang.automobile_management_system.service.orderService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarOrder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.sql.Timestamp;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/1 23:10
 */
@Repository
public interface OrderService {
    //增
    int insertOrder(CarOrder carOrder);
    //删
    int deleteOrder(String id);
    //改
    int updateOrder(String id,CarOrder carOrder);
    //查
    CarOrder selectOrderById(String id);
    List<CarOrder> selectOrderByEmployeeId(String id);

    PageInfo<CarOrder> selectAllCarOrder(Integer index);
    PageInfo<CarOrder> selectAllCarOrder(Integer index,String id);

    PageInfo<CarOrder> selectOrderLike(String keyword,Integer index);
    List<CarOrder> selectAllOrder();
    PageInfo<CarOrder> selectOrderLike(String keyword,String id,Integer index);

    BigDecimal selectAllSalary(String month);
}
