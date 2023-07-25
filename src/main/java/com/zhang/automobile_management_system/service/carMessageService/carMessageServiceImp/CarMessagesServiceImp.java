package com.zhang.automobile_management_system.service.carMessageService.carMessageServiceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarMessagesExample;
import com.zhang.automobile_management_system.bean.CarOrder;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 汽车信息页面功能实现
 * @throws
 * @date 2023/3/30 19:35
 */
@Service
public class CarMessagesServiceImp extends BaseService implements CarMessagesService {


    @Override
    public int saveCarMessage(CarMessages carMessages) {
        int insert = carMessagesMapper.insert(carMessages);
        return insert;
    }

    /**
     * @description:
     * @return: 车辆信息的删除
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 12:32
     */
    @Override
    public int deleteCarMessage(String id) {
        int delete = carMessagesMapper.deleteByPrimaryKey(id);

        return delete;
    }

    @Override
    public int updateCarMessage(String id, CarMessages carMessages) {
        CarMessagesExample.Criteria criteria = carMessagesExample.createCriteria();
        criteria.andCarIdEqualTo(id);
        int update = carMessagesMapper.updateByExampleSelective(carMessages, carMessagesExample);
        carMessagesExample.clear();
        return update;
    }

    @Override
    public PageInfo<CarMessages> getCarMessage(String id) {
        PageHelper.startPage(1, 10);
        carMessagesExample.createCriteria().andCarIdEqualTo(id);
        List<CarMessages> carMessages = carMessagesMapper.selectByExample(carMessagesExample);
        carMessagesExample.clear();
        return new PageInfo<CarMessages>(carMessages,5);
    }

    @Override
    public CarMessages getCarMessageById(String id) {
        CarMessages carMessages = carMessagesMapper.selectByPrimaryKey(id);
        return  carMessages;
    }

    @Override
    public PageInfo<CarMessages> getAllCarMessage(Integer index) {
        PageHelper.startPage(index, 10);
        List<CarMessages> carMessages = carMessagesMapper.selectByExample(null);
        return new PageInfo<>(carMessages, 5);
    }

    @Override
    public PageInfo<CarMessages> selectLike(String keyword, Integer index) {

        carMessagesExample.createCriteria().andCarIdLike(keyword);
        carMessagesExample.or().andCarNameLike(keyword);
        carMessagesExample.or().andCarStockIdLike(keyword);
        carMessagesExample.or().andCarBrandLike(keyword);
        carMessagesExample.or().andCarTypeLike(keyword);
        carMessagesExample.or().andCarEnergyLike(keyword);
        carMessagesExample.or().andCarGearboxLike(keyword);
        carMessagesExample.or().andCarColorLike(keyword);

        carMessagesExample.or().andCarDisplacementIn(Util.extractMath(keyword));
        carMessagesExample.or().andCarVersionIn(Util.extractMath(keyword));
        carMessagesExample.or().andCarPriceIn(Util.extractMath(keyword));

        PageHelper.startPage(index, 10);
        List<CarMessages> carMessages = carMessagesMapper.selectByExample(carMessagesExample);
        carMessagesExample.clear();
        return new PageInfo<>(carMessages, 5);
    }
}
