package com.zhang.automobile_management_system.service.carMessageSaleService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarMessagesSale;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/12 13:28
 */
@Repository
public interface CarMessageSaleService {
    int insertCarMessage(CarMessagesSale carMessage);
    int deleteCarMessage(String id);
    CarMessagesSale selectById(String id);
    PageInfo<CarMessagesSale> selectAllMessage(Integer index);
    PageInfo<CarMessagesSale> selectAllMessage(Integer index,String id);
    PageInfo<CarMessagesSale> selectMessageLike(String keyword,Integer index);
    PageInfo<CarMessagesSale> selectMessageLike(String keyword,Integer index,String id);
}
