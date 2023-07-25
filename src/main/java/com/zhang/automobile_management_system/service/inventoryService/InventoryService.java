package com.zhang.automobile_management_system.service.inventoryService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.Inventory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/6 22:05
 */
@Repository
public interface InventoryService {
    String selectInventoryName(String name, String type, String brand, BigDecimal version,String provide);

    PageInfo<Inventory> selectAllMessage(Integer index);

    void updateCountAdd(String inventoryName,CarMessages carMessages);
    void updateCountSubtract(String inventoryName);

    int insertInventory(CarMessages carMessages);

    PageInfo<Inventory> selectLike(String keyword, Integer index);

    int deleteInventory(String id);
}
