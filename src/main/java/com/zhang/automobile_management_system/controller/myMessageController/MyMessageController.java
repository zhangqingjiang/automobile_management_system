package com.zhang.automobile_management_system.controller.myMessageController;

import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.CarMessagesSale;
import com.zhang.automobile_management_system.bean.CarOrder;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.carMessageSaleService.CarMessageSaleService;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import com.zhang.automobile_management_system.service.orderService.OrderService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 实现对个人设置的操作
 * @throws
 * @date 2023/3/31 20:11
 */
@Controller
public class MyMessageController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CarMessagesService carMessagesService;

    @Autowired
    private CarMessageSaleService carMessageSaleService;

    @Value("${x2}")
    private BigDecimal x2;
    @Value("${x3}")
    private BigDecimal x3;
    @Value("${x4}")
    private BigDecimal x4;
    @Value("${x5}")
    private BigDecimal x5;
    /**
     * @description:
     * @return: 查看个人的信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/14 21:48
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping(value = "/my_message")
    public String jumpMyMessage(ModelMap modelMap, HttpServletRequest request){
        HttpSession session = request.getSession();
        Employee employee = (Employee)session.getAttribute("employee");
        Integer permission = (Integer)session.getAttribute("permission");
        //计算员工提成
        List<CarOrder> carOrders = orderService.selectOrderByEmployeeId(employee.getEmployeeId());//获取员工经手的所有订单



        ArrayList<CarOrder> thisMothOrder = new ArrayList<>();//上月订单集合
        ArrayList<CarOrder> lastMothOrder = new ArrayList<>();//本月订单集合
        BigDecimal royaltyThis = employee.getEmployeeSalary();//本月月员工工资
        BigDecimal royaltyLast = employee.getEmployeeSalary();//上月员工工资
        String thisDateMonth = Util.getThisDateMonth(new Date());//获取当前年月份
        String lastDateMonth = Util.getLastDateMonth(new Date());//获取上个年月份
        if (permission == 5) {
            for (int i = 0; i < carOrders.size(); i++) {
                if (thisDateMonth.equals(carOrders.get(i).getOrderDate().substring(0, 7)) && !"已失败".equals(carOrders.get(i).getOrderState())) {
                    thisMothOrder.add(carOrders.get(i));
                } else if (lastDateMonth.equals(employeeService.selectEmployee(employee.getEmployeeId()).getEmployeeDate().substring(0,7)) &&
                        lastDateMonth.equals(carOrders.get(i).getOrderDate().substring(0, 7)) && !"已失败".equals(carOrders.get(i).getOrderState())) {
                    lastMothOrder.add(carOrders.get(i));
                }else {
                    royaltyLast = new BigDecimal(0.00);
                }
            }
            //计算本月工资
            for (int i = 0; i < thisMothOrder.size(); i++) {
                if (thisMothOrder.get(i).getOrderCarId() != null) {
                    BigDecimal carPrice = carMessageSaleService.selectById(thisMothOrder.get(i).getOrderCarId()).getCarPrice();
                    royaltyThis = royaltyThis.add(carPrice.multiply(x5));
                }
            }
            //计算上一个月工资
            for (int i = 0; i < lastMothOrder.size(); i++) {

                if (lastDateMonth.equals(employeeService.selectEmployee(employee.getEmployeeId()).getEmployeeDate().substring(0,7)) &&
                        lastMothOrder.get(i).getOrderCarId() != null) {
                    BigDecimal carPrice = carMessageSaleService.selectById(lastMothOrder.get(i).getOrderCarId()).getCarPrice();
                    royaltyLast = royaltyLast.add(carPrice.multiply(x5));
                }else {
                    royaltyLast = new BigDecimal(0.00);
                }
            }
        }
        if (permission == 4){
            royaltyThis = employee.getEmployeeSalary().add(orderService.selectAllSalary(thisDateMonth).multiply(x4));
            if (lastDateMonth.equals(employeeService.selectEmployee(employee.getEmployeeId()).getEmployeeDate().substring(0,7))) {
                royaltyLast = employee.getEmployeeSalary().add(orderService.selectAllSalary(lastDateMonth).multiply(x4));
            }else {
                royaltyLast = new BigDecimal(0.00);
            }
        }
        if (permission == 3){
            royaltyThis = employee.getEmployeeSalary().add(orderService.selectAllSalary(thisDateMonth).multiply(x3));
            if (lastDateMonth.equals(employeeService.selectEmployee(employee.getEmployeeId()).getEmployeeDate().substring(0,7))) {
                royaltyLast = employee.getEmployeeSalary().add(orderService.selectAllSalary(lastDateMonth).multiply(x3));
            }else {
                royaltyLast = new BigDecimal(0.00);
            }
        }
        if (permission == 2){
            royaltyThis = employee.getEmployeeSalary().add(orderService.selectAllSalary(thisDateMonth).multiply(x2));
            if (lastDateMonth.equals(employeeService.selectEmployee(employee.getEmployeeId()).getEmployeeDate().substring(0,7))) {
                royaltyLast = employee.getEmployeeSalary().add(orderService.selectAllSalary(lastDateMonth).multiply(x2));
            }else {
                royaltyLast = new BigDecimal(0.00);
            }
        }

        royaltyThis = royaltyThis.setScale(2,BigDecimal.ROUND_HALF_UP);
        royaltyLast = royaltyLast.setScale(2,BigDecimal.ROUND_HALF_UP);

        modelMap.addAttribute("royaltyThisD", royaltyThis);
        modelMap.addAttribute("royaltyLastD", royaltyLast);
        x2 = new BigDecimal(String.valueOf(x2)).setScale(5, BigDecimal.ROUND_HALF_UP);
        x3 = new BigDecimal(String.valueOf(x3)).setScale(5, BigDecimal.ROUND_HALF_UP);
        x4 = new BigDecimal(String.valueOf(x4)).setScale(5, BigDecimal.ROUND_HALF_UP);
        x5 = new BigDecimal(String.valueOf(x5)).setScale(5, BigDecimal.ROUND_HALF_UP);
        modelMap.addAttribute("x2", x2);
        modelMap.addAttribute("x3", x3);
        modelMap.addAttribute("x4", x4);
        modelMap.addAttribute("x5", x5);

        modelMap.addAttribute("employee",employee);
        return "my_message/my_message";
    }

    /**
     * @description:
     * @return: 前往修改账号密码
     * @param:
     * @author ZQJ
     * @date: 2023/4/16 20:35
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping("/my_message_edit")
    public String jumpMyMessageEdit(){
        return "my_message/my_message_edit";
    }

    @PermissionLimit(value = {"B","C","D","E"})
    @PostMapping("/my_message_edit")
    public String MyMessageEdit(@RequestParam("employeeId")String employeeId
            ,@RequestParam("employeePhone")String employeePhone
            ,@RequestParam("password")String password
            ,HttpServletRequest request
            ,ModelMap modelMap){
        if (employeeService.selectEmployee(employeeId) == null){
            modelMap.addAttribute("msg","员工的编号不存在，请仔细检查");
            return "/my_message_edit";
        }
        Employee employee =  (Employee)request.getSession().getAttribute("employee");
        employee.setEmployeePhone(password);
        employee.setEmployeePhone(employeePhone);
        employeeService.updateEmployee(employeeId,employee);
        return "/leave";
    }
}
