package com.zhang.automobile_management_system.service.afterSaleService.afterSaleServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.AfterSale;
import com.zhang.automobile_management_system.bean.AfterSaleExample;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.afterSaleService.AfterSaleService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 方法的实现
 * @throws
 * @date 2023/4/1 22:30
 */
@Service
public class AfterSaleServiceImpl extends BaseService implements AfterSaleService {
    //增
    public int saveAfterSale(AfterSale afterSale){
        int insert = afterSaleMapper.insert(afterSale);
        return insert;
    }

    //删
    public int deleteAfterSale(Integer id){
        int delete = afterSaleMapper.deleteByPrimaryKey(id);
        return delete;
    }
    //改:通过id查询对象，将新的对象afterSale添加到当前
    public int updateAfterSale(Integer id,AfterSale afterSale) {
        AfterSaleExample.Criteria criteria = afterSaleExample.createCriteria();
        criteria.andAfterSaleEmployeeIdEqualTo(String.valueOf(id));
        int update = afterSaleMapper.updateByExampleSelective(afterSale, afterSaleExample);
        afterSaleExample.clear();
        return update;
    }

    /**
     * @description:
     * @return: 查询全部的售后信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/15 17:52
     */
    public PageInfo<AfterSale> selectAllAfterSale(Integer index){
        PageHelper.startPage(index, 30);
        List<AfterSale> afterSales = afterSaleMapper.selectByExample(null);
        return new PageInfo<>(afterSales,5 );
    }

    /**
     * @description:
     * @return: 根据员工的Id查询他服务过的信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/15 17:51
     */
    @Override
    public PageInfo<AfterSale> selectAllAfterSale(Integer index, String id) {
        PageHelper.startPage(index, 30);
        afterSaleExample.createCriteria().andAfterSaleEidEqualTo(id);
        List<AfterSale> afterSales = afterSaleMapper.selectByExample(afterSaleExample);
        afterSaleExample.clear();
        return new PageInfo<>(afterSales,5 );
    }

    /**
     * @description:
     * @return: 模糊查询
     * @param:
     * @author ZQJ
     * @date: 2023/4/10 23:04
     */
    @Override
    public PageInfo<AfterSale> selectAfterSaleLike(Integer index, String keyword) {
        PageHelper.startPage(index, 30);
        afterSaleExample.createCriteria().andAfterSaleDateLike(keyword);
        afterSaleExample.or().andAfterSaleEmployeeIdLike(keyword);
        afterSaleExample.or().andAfterSaleStateLike(keyword);
        afterSaleExample.or().andAfterSaleCarIdLike(keyword);
        afterSaleExample.or().andAfterSaleEidLike(keyword);
        List<AfterSale> afterSales = afterSaleMapper.selectByExample(afterSaleExample);
        afterSaleExample.clear();
        return new PageInfo<>(afterSales,5);
    }

    /**
     * @description:
     * @return: 根据Id模糊查询自己相关信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/15 17:59
     */
    @Override
    public PageInfo<AfterSale> selectAfterSaleLike(Integer index, String keyword, String id) {
        PageHelper.startPage(index, 30);
        afterSaleExample.createCriteria().andAfterSaleDateLike(keyword);
        afterSaleExample.or().andAfterSaleEmployeeIdLike(keyword);

        afterSaleExample.or().andAfterSaleEidEqualTo(id);

        afterSaleExample.or().andAfterSaleStateLike(keyword);
        afterSaleExample.or().andAfterSaleCarIdLike(keyword);
        afterSaleExample.or().andAfterSaleEidLike(keyword);
        List<AfterSale> afterSales = afterSaleMapper.selectByExample(afterSaleExample);
        afterSaleExample.clear();
        return new PageInfo<>(afterSales,5);
    }

    //查一条
    public AfterSale selectAfterSale(Integer id){
        AfterSale afterSale = afterSaleMapper.selectByPrimaryKey(id);
        return afterSale;
    }

    /**
     * @description:
     * @return: 通过Id和时间查询售后信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/13 17:32
     */
    @Override
    public AfterSale selectAfterSaleByIdAndDate(String date, String id) {
        afterSaleExample.createCriteria().andAfterSaleDateEqualTo(date)
                .andAfterSaleClientIdEqualTo(id);
        List<AfterSale> afterSales = afterSaleMapper.selectByExample(afterSaleExample);
        if (afterSales.size() != 0){
            return afterSales.get(0);
        }
        else {
            return null;
        }
    }
}
