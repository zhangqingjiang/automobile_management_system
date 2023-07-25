package com.zhang.automobile_management_system.controller.statisticsController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarMessagesSale;
import com.zhang.automobile_management_system.bean.CarOrder;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.controller.afterSaleController.CarMessageSale;
import com.zhang.automobile_management_system.service.carMessageSaleService.CarMessageSaleService;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import com.zhang.automobile_management_system.service.orderService.OrderService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/23 19:04
 */
@Controller
public class StatisticsController {
    @Autowired
    OrderService orderService;
    @Autowired
    CarMessageSaleService carMessageSaleService;
    @Autowired
    EmployeeService employeeService;
    @Value("${x2}")
    private BigDecimal x2;
    @Value("${x3}")
    private BigDecimal x3;
    @Value("${x4}")
    private BigDecimal x4;
    @Value("${x5}")
    private BigDecimal x5;

    @PermissionLimit({"A", "B", "C"})
    @GetMapping("/statisticsCarMessage")
    @ResponseBody
    public String getStatisticsCarMessage() throws JSONException {
        //获取车辆销售总额
        JSONObject jsonObject = new JSONObject();
        BigDecimal bigDecimal = new BigDecimal(0.00);
        for (int j = 12; j >= 0; j--) {
            String s = Util.generateLastDate(j);
            List<CarOrder> carOrders = orderService.selectAllOrder();
            for (CarOrder carOrder : carOrders) {
                if (s.equals(carOrder.getOrderDate().substring(0, 7))) {
                    bigDecimal = bigDecimal.add(carMessageSaleService.selectById(carOrder.getOrderCarId()).getCarPrice());
                }
            }
            bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            jsonObject.put(s,bigDecimal);
        }
        return jsonObject.toString();
    }
    @PermissionLimit({"A", "B", "C"})
    @GetMapping("/statisticsEmployeeMessage")
    @ResponseBody
    public String getStatisticsEmployeeMessage() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        BigDecimal bigDecimal = new BigDecimal(0.00);
        for (int j = 12; j >=0; j--) {
            String s = Util.generateLastDate(j);
            List<Employee> employees = employeeService.selectEmployeeLike(s);
            for (Employee employee : employees) {
                if ("E".equals(employee.getEmployeePermission())) {
                    List<CarOrder> carOrders = orderService.selectOrderByEmployeeId(employee.getEmployeeId());
                    BigDecimal big = new BigDecimal(0.00);
                    for (CarOrder carOrder : carOrders){
                        big = big.add(carMessageSaleService.selectById(carOrder.getOrderCarId()).getCarPrice());
                    }
                    bigDecimal = bigDecimal.add(employee.getEmployeeSalary().add(big.multiply(x5)));
                }else if("D".equals(employee.getEmployeePermission())){
                    List<CarOrder> carOrders = orderService.selectAllOrder();
                    BigDecimal big = new BigDecimal(0.00);
                    for (CarOrder carOrder : carOrders){
                        big = big.add(carMessageSaleService.selectById(carOrder.getOrderCarId()).getCarPrice());
                    }
                    bigDecimal = bigDecimal.add(employee.getEmployeeSalary().add(big.multiply(x4)));
                }else if("C".equals(employee.getEmployeePermission())){
                    List<CarOrder> carOrders = orderService.selectAllOrder();
                    BigDecimal big = new BigDecimal(0.00);
                    for (CarOrder carOrder : carOrders){
                        big = big.add(carMessageSaleService.selectById(carOrder.getOrderCarId()).getCarPrice());
                    }
                    bigDecimal = bigDecimal.add(employee.getEmployeeSalary().add(big.multiply(x3)));
                }else if("B".equals(employee.getEmployeePermission())){
                    List<CarOrder> carOrders = orderService.selectAllOrder();
                    BigDecimal big = new BigDecimal(0.00);
                    for (CarOrder carOrder : carOrders){
                        big = big.add(carMessageSaleService.selectById(carOrder.getOrderCarId()).getCarPrice());
                    }
                    bigDecimal = bigDecimal.add(employee.getEmployeeSalary().add(big.multiply(x2)));
                }
            }
            bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            jsonObject.put(String.valueOf(j), bigDecimal);
        }
        return jsonObject.toString();
    }


    @PermissionLimit({"A", "B", "C"})
    @GetMapping(value = "/statistics")
    public String jumpStatistics(){
        return "statistics/statistics";
    }
}
