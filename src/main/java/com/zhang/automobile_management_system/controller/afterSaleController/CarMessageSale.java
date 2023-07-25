package com.zhang.automobile_management_system.controller.afterSaleController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarMessagesSale;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.carMessageSaleService.CarMessageSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 车辆销售后的展示
 * @throws
 * @date 2023/4/12 15:45
 */
@Controller
public class CarMessageSale {

    @Autowired
    CarMessageSaleService carMessageSaleService;


/**
 * @description:
 * @return: 获取售后车辆信息，普通员工只能获取自己销售的车辆的信息
 * @param:
 * @author ZQJ
 * @date: 2023/4/14 18:26
 */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping("/carMessageSale")
    public String jumpCarMessageSale(ModelMap modelMap, HttpServletRequest request,
                                     @RequestParam(value = "index", defaultValue = "1") Integer index,
                                     @RequestParam(value = "keyword", defaultValue = "") String keyword) throws NoSuchMethodException {
        HttpSession session = request.getSession();
        //获取员工的Id
        Employee employee = (Employee)session.getAttribute("employee");

        if (session.getAttribute("keyword") != null) {
            keyword = (String)session.getAttribute("keyword");
            session.removeAttribute("keyword");
        }
        if (session.getAttribute("index") != null ){
            index = (Integer)session.getAttribute("index");
            session.removeAttribute("index");
        }
        if (keyword.length() != 0){

            if(employee.getEmployeePermission().equals("E")) {
                PageInfo<CarMessagesSale> carMessagesSalePageInfo = carMessageSaleService.selectMessageLike(keyword, index,employee.getEmployeeId());
                modelMap.addAttribute("keyword", keyword);
                modelMap.addAttribute("list", carMessagesSalePageInfo);
            }else {
                PageInfo<CarMessagesSale> carMessagesSalePageInfo = carMessageSaleService.selectMessageLike(keyword, index);
                modelMap.addAttribute("keyword", keyword);
                modelMap.addAttribute("list", carMessagesSalePageInfo);
            }
        }else {
            if (employee.getEmployeePermission().equals("E")) {
                PageInfo<CarMessagesSale> carMessagesSalePageInfo = carMessageSaleService.selectAllMessage(index, employee.getEmployeeId());
                modelMap.addAttribute("list", carMessagesSalePageInfo);
            }else {
                PageInfo<CarMessagesSale> carMessagesSalePageInfo = carMessageSaleService.selectAllMessage(index);
                modelMap.addAttribute("list", carMessagesSalePageInfo);
            }

        }
        return "afterSaleMessage/afterSaleCarMessage";
    }
}
