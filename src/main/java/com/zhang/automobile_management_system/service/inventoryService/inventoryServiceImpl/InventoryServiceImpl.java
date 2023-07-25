package com.zhang.automobile_management_system.service.inventoryService.inventoryServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.Inventory;
import com.zhang.automobile_management_system.bean.InventoryExample;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.inventoryService.InventoryService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/6 22:07
 */
@Service
public class InventoryServiceImpl extends BaseService implements InventoryService {

    @Autowired
    Inventory inventory;

    /**
     * @description:
     * @return: 通过名称，品牌，版本，类型查询车库
     * @param:
     * @author ZQJ
     * @date: 2023/4/6 22:08
     */
    @Override
    public String selectInventoryName(String name, String type, String brand, BigDecimal version, String provide) {

        inventoryExample.createCriteria().andInventoryBrandEqualTo(brand)
                .andCarProvideEqualTo(provide)
                .andInventoryCarNameEqualTo(name)
                .andInventoryTypeEqualTo(type)
                .andInventoryVersionEqualTo(version);
        List<Inventory> inventories = inventoryMapper.selectByExample(inventoryExample);
        inventoryExample.clear();

        if (inventories.size() != 0) {
            return inventories.get(0).getInventoryName();
        } else {
            return "";
        }
    }



    /**
    * @Description
     * @Param index
     * @return PageInfo<com.zhang.automobile_management_system.bean.Inventory>
     * @Author zqj
     * @Date 2023/4/26 12:36
    */
    public PageInfo<Inventory> selectAllMessage(Integer index) {
        PageHelper.startPage(index, 10);
        List<Inventory> inventories = inventoryMapper.selectByExample(null);
        return new PageInfo<>(inventories, 5);
    }

    /**
     * @description:
     * @return: 增加库存数量
     * @param: 
     * @author ZQJ
     * @date: 2023/4/6 22:42
     */
    @Override
    public void updateCountAdd(String inventoryName,CarMessages carMessages) {
        InventoryExample.Criteria criteria = inventoryExample.createCriteria();
        criteria.andInventoryNameEqualTo(inventoryName);
        List<Inventory> inventories = inventoryMapper.selectByExample(inventoryExample);
        Inventory inventory = null;
        if (inventories.size() != 0) {
            inventory = inventories.get(0);
            inventory.setInventoryCount(inventory.getInventoryCount() + 1);
            inventoryMapper.updateByExample(inventory, inventoryExample);
            inventoryExample.clear();
        }else {
            insertInventory(carMessages);
        }
    }

    /**
     * @description:
     * @return: 减少库存数量
     * @param:
     * @author ZQJ
     * @date: 2023/4/6 23:03
     */
    @Override
    public void updateCountSubtract(String inventoryName) {
        inventoryExample.createCriteria().andInventoryNameEqualTo(inventoryName);
        List<Inventory> inventories = inventoryMapper.selectByExample(inventoryExample);
        Inventory inventory = inventories.get(0);
        inventory.setInventoryCount(inventory.getInventoryCount() - 1);
        inventoryMapper.updateByExample(inventory, inventoryExample);
        inventoryExample.clear();
    }

    /**
     * @description:
     * @return: 创建一条记录
     * @param:
     * @author ZQJ
     * @date: 2023/4/6 22:43
     */

    public int insertInventory(CarMessages carMessages) {
        inventory.setInventoryBrand(carMessages.getCarBrand());
        inventory.setInventoryCarName(carMessages.getCarName());
        inventory.setInventoryName(carMessages.getCarStockId());
        inventory.setInventoryType(carMessages.getCarType());
        inventory.setInventoryVersion(carMessages.getCarVersion());
        inventory.setCarProvide(carMessages.getCarProvide());
        inventory.setInventoryCount(1);
        int i = inventoryMapper.insert(inventory);
        return i;
    }

    /**
     * @description:
     * @return: 模糊查询
     * @param:
     * @author ZQJ
     * @date: 2023/4/6 23:14
     */
    @Override
    public PageInfo<Inventory> selectLike(String keyword, Integer index) {
        PageHelper.startPage(index, 10);
        inventoryExample.createCriteria().andInventoryNameLike(keyword);
        inventoryExample.or().andInventoryTypeLike(keyword);
        inventoryExample.or().andInventoryBrandLike(keyword);

        inventoryExample.or().andInventoryCountIn(Util.extractMathInteger(keyword));

        inventoryExample.or().andInventoryCarNameLike(keyword);
        inventoryExample.or().andInventoryVersionIn(Util.extractMath(keyword));

        List<Inventory> inventories = inventoryMapper.selectByExample(inventoryExample);
        inventoryExample.clear();
        return new PageInfo<>(inventories, 5);
    }

    /**
     * @description:
     * @return: 根据库名删除库
     * @param:
     * @author ZQJ
     * @date: 2023/4/7 21:25
     */
    @Override
    public int deleteInventory(String id) {
        inventoryExample.createCriteria().andInventoryNameEqualTo(id);
        int delete = inventoryMapper.deleteByExample(inventoryExample);
        inventoryExample.clear();
        return delete;
    }
}
