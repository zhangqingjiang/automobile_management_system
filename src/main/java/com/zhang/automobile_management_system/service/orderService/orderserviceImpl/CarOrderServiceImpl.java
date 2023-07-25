package com.zhang.automobile_management_system.service.orderService.orderserviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarOrder;
import com.zhang.automobile_management_system.bean.CarOrderExample;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.carMessageSaleService.CarMessageSaleService;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;
import com.zhang.automobile_management_system.service.orderService.OrderService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/1 23:15
 */
@Service
public class CarOrderServiceImpl extends BaseService implements OrderService {

    @Autowired
    CarMessageSaleService carMessageSaleService;

    @Override
    public int insertOrder(CarOrder carOrder) {
        int insert = carOrderMapper.insert(carOrder);
        return insert;
    }

    @Override
    public int deleteOrder(String id) {
        int delete = carOrderMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public int updateOrder(String id, CarOrder carOrder) {
        carOrderExample.createCriteria().andOrderIdEqualTo(id);
        int update = carOrderMapper.updateByExample(carOrder, carOrderExample);
        carOrderExample.clear();
        return update;
    }

    @Override
    public CarOrder selectOrderById(String id) {
        CarOrder carOrder = carOrderMapper.selectByPrimaryKey(id);
        return carOrder;
    }

    @Override
    public List<CarOrder> selectOrderByEmployeeId(String id) {
        carOrderExample.createCriteria().andOrderEmployeeIdEqualTo(id);
        List<CarOrder> carOrders = carOrderMapper.selectByExample(carOrderExample);
        return carOrders;
    }


    @Override
    public PageInfo<CarOrder> selectAllCarOrder(Integer index) {
        PageHelper.startPage(index,10);
        List<CarOrder> carOrders = carOrderMapper.selectByExample(null);
        return new PageInfo<>(carOrders);
    }

    /**
     * @description:
     * @return: 实现通过ID查询员工的经手的订单
     * @param:
     * @author ZQJ
     * @date: 2023/4/15 21:05
     */
    @Override
    public PageInfo<CarOrder> selectAllCarOrder(Integer index, String id) {
        PageHelper.startPage(index,10);
        carOrderExample.createCriteria().andOrderEmployeeIdEqualTo(id);
        List<CarOrder> carOrders = carOrderMapper.selectByExample(carOrderExample);
        return new PageInfo<>(carOrders,5);
    }

    @Override
    public PageInfo<CarOrder> selectOrderLike(String keyword, Integer index) {
        PageHelper.startPage(index,10);
        carOrderExample.createCriteria().andOrderCarIdLike(keyword);
        carOrderExample.or().andOrderEmployeeIdLike(keyword);
        carOrderExample.or().andOrderCarIdLike(keyword);
        carOrderExample.or().andOrderStateLike(keyword);
        List<CarOrder> carOrders = carOrderMapper.selectByExample(carOrderExample);
        carOrderExample.clear();
        return new PageInfo<>(carOrders,5);
    }

    @Override
    public PageInfo<CarOrder> selectOrderLike(String keyword,String id,Integer index) {
        PageHelper.startPage(index,10);
        carOrderExample.createCriteria().andOrderCarIdLike(keyword);
        carOrderExample.or().andOrderEmployeeIdLike(keyword);

        carOrderExample.or().andOrderEmployeeIdEqualTo(id);

        carOrderExample.or().andOrderCarIdLike(keyword);
        carOrderExample.or().andOrderStateLike(keyword);
        List<CarOrder> carOrders = carOrderMapper.selectByExample(carOrderExample);
        carOrderExample.clear();
        return new PageInfo<>(carOrders,5);
    }

    @Override
    public List<CarOrder> selectAllOrder() {
        List<CarOrder> carOrders = carOrderMapper.selectByExample(null);
        return carOrders;
    }


    
    /**
     * @description: 
     * @return: 根据时间查公司总销售额
     * @param: 
     * @author ZQJ
     * @date: 2023/4/15 18:14
     */
    public BigDecimal selectAllSalary(String month){
        BigDecimal bigDecimal = new BigDecimal(0.00);
        List<CarOrder> carOrders = carOrderMapper.selectByExample(null);
        for (int i = 0; i < carOrders.size(); i++) {
            if (month.equals(carOrders.get(i).getOrderDate().substring(0,7)) && !"已失败".equals(carOrders.get(i).getOrderState())){
                bigDecimal.add(carMessageSaleService.selectById(carOrders.get(i).getOrderCarId()).getCarPrice());
            }
        }
        return bigDecimal;
    }
}
