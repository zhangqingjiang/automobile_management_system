package com.zhang.automobile_management_system.service.statisticsService.statisticsServiceImpl;

import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.statisticsService.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/23 19:51
 */
@Service
public class StatisticsServiceImpl extends BaseService implements StatisticsService {
    @Override
    public TreeMap<Integer, Integer> getMonthAndAllSalary() {
        return null;
    }

    @Override
    public TreeMap<Integer, Integer> getMonthAndAllSale() {
        return null;
    }
}
