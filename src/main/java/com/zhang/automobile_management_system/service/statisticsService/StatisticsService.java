package com.zhang.automobile_management_system.service.statisticsService;

import org.springframework.stereotype.Repository;

import java.util.TreeMap;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/23 19:47
 */
@Repository
public interface StatisticsService {

    TreeMap<Integer,Integer> getMonthAndAllSalary();

    TreeMap<Integer,Integer> getMonthAndAllSale();

}
