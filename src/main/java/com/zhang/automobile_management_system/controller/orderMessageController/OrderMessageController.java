package com.zhang.automobile_management_system.controller.orderMessageController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.*;
import com.zhang.automobile_management_system.service.carMessageSaleService.CarMessageSaleService;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;
import com.zhang.automobile_management_system.service.clientService.ClientService;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import com.zhang.automobile_management_system.service.inventoryService.InventoryService;
import com.zhang.automobile_management_system.service.orderService.OrderService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 处理订单页面
 * @throws
 * @date 2023/4/7 21:09
 */
@Controller
public class OrderMessageController {

    static String DEFAULT_STATE = "已完成";

    @Autowired
    OrderService orderService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CarMessagesService carMessagesService;
    @Autowired
    ClientService clientService;
    @Autowired
    CarMessageSaleService carMessageSaleService;
    @Autowired
    CarMessagesSale carMessagesSale;
    @Autowired
    CarMessages carMessages;

    /**
     * @description:
     * @return: 查询订单的信息，普通员工只能查询自己处理的订单的信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/7 22:48
     */
    @PermissionLimit(value = {"A", "B", "C", "D", "E"})
    @GetMapping(value = "/orderMessage")
    public String jumpOrderMessage(ModelMap modelMap, HttpServletRequest request, @RequestParam(value = "index", defaultValue = "1") Integer index, @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        HttpSession session = request.getSession();
        Integer permission = (Integer) session.getAttribute("permission");
        Employee employee = (Employee) session.getAttribute("employee");
        String flag = "";
        if (session.getAttribute("keyword") != null) {
            keyword = (String) session.getAttribute("keyword");
            session.removeAttribute("keyword");
        }
        if (session.getAttribute("index") != null) {
            index = (Integer) session.getAttribute("index");
            session.removeAttribute("index");
        }
        if (session.getAttribute("flag") != null) {
            flag = (String) session.getAttribute("flag");
            session.removeAttribute("flag");
        }
        if (permission == 5) {
            if (keyword.length() != 0 && ("order".equals(flag) || "".equals(flag))) {
                PageInfo<CarOrder> carOrderPageInfo = orderService.selectOrderLike(keyword, employee.getEmployeeId(), index);
                modelMap.addAttribute("keyword", keyword);
                modelMap.addAttribute("list", carOrderPageInfo);
            } else {
                PageInfo<CarOrder> carOrderPageInfo = orderService.selectAllCarOrder(index, employee.getEmployeeId());
                modelMap.addAttribute("list", carOrderPageInfo);
            }
        } else {
            if (keyword.length() != 0 && ("order".equals(flag) || "".equals(flag))) {
                PageInfo<CarOrder> carOrderPageInfo = orderService.selectOrderLike(keyword, index);
                modelMap.addAttribute("keyword", keyword);
                modelMap.addAttribute("list", carOrderPageInfo);
            } else {
                PageInfo<CarOrder> carOrderPageInfo = orderService.selectAllCarOrder(index);
                modelMap.addAttribute("list", carOrderPageInfo);
            }
        }
        return "orderMessage/orderMessage";
    }

    /**
     * @description:
     * @return: 添加订单信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/7 22:48
     */
    //前往添加页面
    @PermissionLimit(value = {"A", "B", "C", "D"})
    @GetMapping(value = "/orderMessageAdd")
    public String orderMessageAdd(Client client, CarOrder carOrder, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Object success = session.getAttribute("success");
        if (success != null) {
            session.removeAttribute("success");
            model.addAttribute("success", success);
        }
        return "orderMessage/orderMessageAdd";
    }

    //添加数据
    @PermissionLimit(value = {"A", "B", "C", "D"})
    @PostMapping(value = "/orderMessage")
    public String insertOrderMessage(CarOrder carOrder, Client client, ModelMap model, HttpServletRequest request, @RequestParam(value = "index", defaultValue = "1") Integer index) {
        if (employeeService.selectEmployee(carOrder.getOrderEmployeeId()) == null) {
            model.addAttribute("msg", "员工的信息未查询到请仔细检查");
            model.addAttribute("carOrder", carOrder);
            model.addAttribute("client", client);
            return "orderMessage/orderMessageAdd";
        }
        if (carMessagesService.getCarMessageById(carOrder.getOrderCarId()) == null) {
            model.addAttribute("msg", "车辆信息未查询到亲仔细检查");
            model.addAttribute("carOrder", carOrder);
            model.addAttribute("client", client);
            return "orderMessage/orderMessageAdd";
        }
        //用户ID的生成
        String clientId = Util.generateClientId(clientService);
        client.setClientId(clientId);
        clientService.insertClient(client);

        //订单日期的生成
        carOrder.setOrderId(clientId);
        long date = new Date().getTime();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        carOrder.setOrderDate(format);
        carOrder.setOrderClientId(clientId);
        carOrder.setOrderState(DEFAULT_STATE);

        orderService.insertOrder(carOrder);

        //售后表添加数据
        CarMessages carMessage = carMessagesService.getCarMessageById(carOrder.getOrderCarId());

        carMessagesSale.setCarColor(carMessage.getCarColor());
        carMessagesSale.setCarBrand(carMessage.getCarBrand());
        carMessagesSale.setCarId(carMessage.getCarId());
        carMessagesSale.setCarStockId(carMessage.getCarStockId());
        carMessagesSale.setCarEnergy(carMessage.getCarEnergy());
        carMessagesSale.setCarGearbox(carMessage.getCarGearbox());
        carMessagesSale.setCarDisplacement(carMessage.getCarDisplacement());
        carMessagesSale.setCarName(carMessage.getCarName());
        carMessagesSale.setCarPrice(carMessage.getCarPrice());
        carMessagesSale.setCarType(carMessage.getCarType());
        carMessagesSale.setCarVersion(carMessage.getCarVersion());
        carMessagesSale.setCarProvide(carMessage.getCarProvide());

        carMessageSaleService.insertCarMessage(carMessagesSale);
        //售前表删除数据
        carMessagesService.deleteCarMessage(carOrder.getOrderCarId());
        //更新库中的储量
        inventoryService.updateCountSubtract(carMessage.getCarStockId());

        HttpSession session = request.getSession();
        session.setAttribute("success", "订单提交成功");
        session.setAttribute("flag", "car");
        session.setAttribute("keyword", carOrder.getOrderCarId());
        return "redirect:/orderMessageAdd";
    }

    //添加数据完成之后的跳转到添加的数据的位置
    @PermissionLimit(value = {"A", "B", "C", "D"})
    @GetMapping(value = "orderMessage/max")
    public String jumpCompleteAddOrderMessage(HttpServletRequest request) {
        return "redirect:/orderMessage";
    }


    /**
     * @description:
     * @return: 订单状态的修改，普通员工不能擅自修改订单信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/11 19:45
     */
    @PermissionLimit(value = {"A", "B", "C"})
    @GetMapping(value = "/orderMessageUpdate")
    public String updateOrderMessage(HttpServletRequest request,
                                     @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                     @RequestParam(value = "index", defaultValue = "1") Integer index,
                                     @RequestParam("state") String state,
                                     @RequestParam("orderId") String orderId) {

        CarOrder carOrder = orderService.selectOrderById(orderId);
        if (!state.equals(carOrder.getOrderState())) {

            if ("已完成".equals(state)) {
                //售后表添加数据
                CarMessages carMessage = carMessagesService.getCarMessageById(carOrder.getOrderCarId());
                if (carMessage != null) {
                    carMessagesSale.setCarColor(carMessage.getCarColor());
                    carMessagesSale.setCarBrand(carMessage.getCarBrand());
                    carMessagesSale.setCarId(carMessage.getCarId());
                    carMessagesSale.setCarStockId(carMessage.getCarStockId());
                    carMessagesSale.setCarEnergy(carMessage.getCarEnergy());
                    carMessagesSale.setCarGearbox(carMessage.getCarGearbox());
                    carMessagesSale.setCarDisplacement(carMessage.getCarDisplacement());
                    carMessagesSale.setCarName(carMessage.getCarName());
                    carMessagesSale.setCarPrice(carMessage.getCarPrice());
                    carMessagesSale.setCarType(carMessage.getCarType());
                    carMessagesSale.setCarVersion(carMessage.getCarVersion());
                    carMessagesSale.setCarProvide(carMessage.getCarProvide());

                    carMessageSaleService.insertCarMessage(carMessagesSale);
                    //售前表删除数据
                    carMessagesService.deleteCarMessage(carOrder.getOrderCarId());
                    //更新库中的储量
                    inventoryService.updateCountSubtract(carMessage.getCarStockId());
                }
                carOrder.setOrderState(state);
            } else if ("已失败".equals(state)) {
                //售前表添加数据
                CarMessagesSale carMessagesSale = carMessageSaleService.selectById(carOrder.getOrderCarId());

                if (carMessagesSale != null) {
                    carMessages.setCarBrand(carMessagesSale.getCarBrand());
                    carMessages.setCarStockId(carMessagesSale.getCarStockId());
                    carMessages.setCarColor(carMessagesSale.getCarColor());
                    carMessages.setCarEnergy(carMessagesSale.getCarEnergy());
                    carMessages.setCarId(carMessagesSale.getCarId());
                    carMessages.setCarDisplacement(carMessagesSale.getCarDisplacement());
                    carMessages.setCarName(carMessagesSale.getCarName());
                    carMessages.setCarPrice(carMessagesSale.getCarPrice());
                    carMessages.setCarVersion(carMessagesSale.getCarVersion());
                    carMessages.setCarType(carMessagesSale.getCarType());
                    carMessages.setCarGearbox(carMessagesSale.getCarGearbox());
                    carMessages.setCarProvide(carMessagesSale.getCarProvide());

                    carMessagesService.saveCarMessage(carMessages);
                    //售后表删除数据
                    carMessageSaleService.deleteCarMessage(carOrder.getOrderCarId());
                    //更新库中的储量
                    inventoryService.updateCountAdd(carMessages.getCarStockId(),carMessages);
                }
                carOrder.setOrderState(state);
            }else if ("处理中".equals(state)){
                carOrder.setOrderState(state);
            }
            orderService.updateOrder(orderId, carOrder);
        }

        HttpSession session = request.getSession();
        session.setAttribute("flag", "order");
        session.setAttribute("keyword", keyword);
        session.setAttribute("id", orderId);
        session.setAttribute("index", index);
        return "redirect:/orderMessage";
    }
}
