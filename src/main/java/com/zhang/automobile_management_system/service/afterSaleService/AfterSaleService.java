package com.zhang.automobile_management_system.service.afterSaleService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.AfterSale;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:定义关于售后服务的CRUD方法
 * @throws
 * @date 2023/4/1 22:24
 */
@Repository
public interface AfterSaleService {
    //增
    int saveAfterSale(AfterSale afterSale);
    //删
    int deleteAfterSale(Integer id);
    //改
    int updateAfterSale(Integer id,AfterSale afterSale);
    //查
    PageInfo<AfterSale> selectAllAfterSale(Integer index);
    PageInfo<AfterSale> selectAllAfterSale(Integer index,String id);

    PageInfo<AfterSale> selectAfterSaleLike(Integer index,String keyword);
    PageInfo<AfterSale> selectAfterSaleLike(Integer index,String keyword,String id);

    AfterSale selectAfterSale(Integer id);
    AfterSale selectAfterSaleByIdAndDate(String date, String id);
}
