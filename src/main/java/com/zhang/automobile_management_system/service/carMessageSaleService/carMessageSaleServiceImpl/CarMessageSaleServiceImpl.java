package com.zhang.automobile_management_system.service.carMessageSaleService.carMessageSaleServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarMessagesSale;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.carMessageSaleService.CarMessageSaleService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/12 13:44
 */
@Service
public class CarMessageSaleServiceImpl extends BaseService implements CarMessageSaleService {

    /**
     * @description:
     * @return: 数据插入
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:09
     */
    @Override
    public int insertCarMessage(CarMessagesSale carMessagesSale) {
        int insert = carMessagesSaleMapper.insert(carMessagesSale);
        return insert;
    }

    /**
     * @description:
     * @return: 数据删除
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:10
     */
    @Override
    public int deleteCarMessage(String id) {
        carMessagesSaleExample.createCriteria().andCarIdEqualTo(id);
        int delete = carMessagesSaleMapper.deleteByExample(carMessagesSaleExample);
        carMessagesSaleExample.clear();
        return delete;
    }

    /**
     * @description:
     * @return: 通过id查询数据
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:19
     */
    @Override
    public CarMessagesSale selectById(String id){
        CarMessagesSale select = carMessagesSaleMapper.selectByPrimaryKey(id);
        return select;
    }

    /**
     * @description:
     * @return: 查询所有的车辆信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:47
     */
    @Override
    public PageInfo<CarMessagesSale> selectAllMessage(Integer index){
        PageHelper.startPage(index,10 );
        List<CarMessagesSale> carMessagesSales = carMessagesSaleMapper.selectByExample(null);
        return  new PageInfo<>(carMessagesSales,5);
    }



    /**
     * @description:
     * @return: 有员工编号查询所有的车辆信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:47
     */
    @Override
    public PageInfo<CarMessagesSale> selectAllMessage(Integer index,String id){
        PageHelper.startPage(index,10 );
        carMessagesSaleExample.createCriteria().andCarSaleEmployeeEqualTo(id);
        List<CarMessagesSale> carMessagesSales = carMessagesSaleMapper.selectByExample(carMessagesSaleExample);
        carMessagesSaleExample.clear();
        return  new PageInfo<>(carMessagesSales,5);
    }

    /**
     * @description:
     * @return: 员工权限高于D通过关键词模糊查询
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:51
     */
    @Override
    public PageInfo<CarMessagesSale> selectMessageLike(String keyword,Integer index) {
        PageHelper.startPage(index, 10);
        carMessagesSaleExample.createCriteria().andCarIdLike(keyword);
        carMessagesSaleExample.or().andCarNameLike(keyword);
        carMessagesSaleExample.or().andCarStockIdLike(keyword);
        carMessagesSaleExample.or().andCarSaleEmployeeLike(keyword);
        carMessagesSaleExample.or().andCarBrandLike(keyword);
        carMessagesSaleExample.or().andCarTypeLike(keyword);
        carMessagesSaleExample.or().andCarEnergyEqualTo(keyword);
        carMessagesSaleExample.or().andCarGearboxLike(keyword);
        carMessagesSaleExample.or().andCarColorLike(keyword);
        carMessagesSaleExample.or().andCarProvideLike(keyword);
        carMessagesSaleExample.or().andCarDisplacementIn(Util.extractMath(keyword));
        carMessagesSaleExample.or().andCarVersionIn(Util.extractMath(keyword));
        carMessagesSaleExample.or().andCarPriceIn(Util.extractMath(keyword));

        List<CarMessagesSale> carMessagesSales = carMessagesSaleMapper.selectByExample(carMessagesSaleExample);
        carMessagesSaleExample.clear();
        return new PageInfo<>(carMessagesSales,5);
    }


    /**
     * @description:
     * @return: 员工权限等于D通过关键词模糊查询
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:51
     */
    @Override
    public PageInfo<CarMessagesSale> selectMessageLike(String keyword,Integer index,String id) {
        PageHelper.startPage(index, 10);
        carMessagesSaleExample.createCriteria().andCarIdLike(keyword);
        carMessagesSaleExample.createCriteria().andCarIdLike(keyword);
        carMessagesSaleExample.or().andCarNameLike(keyword);
        carMessagesSaleExample.or().andCarStockIdLike(keyword);
        carMessagesSaleExample.or().andCarBrandLike(keyword);
        carMessagesSaleExample.or().andCarSaleEmployeeEqualTo(id);
        carMessagesSaleExample.or().andCarTypeLike(keyword);
        carMessagesSaleExample.or().andCarEnergyEqualTo(keyword);
        carMessagesSaleExample.or().andCarGearboxLike(keyword);
        carMessagesSaleExample.or().andCarColorLike(keyword);
        carMessagesSaleExample.or().andCarDisplacementIn(Util.extractMath(keyword));
        carMessagesSaleExample.or().andCarVersionIn(Util.extractMath(keyword));
        carMessagesSaleExample.or().andCarPriceIn(Util.extractMath(keyword));

        List<CarMessagesSale> carMessagesSales = carMessagesSaleMapper.selectByExample(carMessagesSaleExample);
        carMessagesSaleExample.clear();
        return new PageInfo<>(carMessagesSales,5);
    }
}
