package com.zhang.automobile_management_system.controller.carMessageContrller;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;

import com.zhang.automobile_management_system.service.inventoryService.InventoryService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 * @author ZQJ
 * @version 1.0
 * @description: 实现登录
 * @throws
 * @date 2023/3/29 23:36
 */
@Controller
public class CarMessageController {

    @Autowired
    public CarMessagesService carMessagesService;
    @Autowired
    public  InventoryService inventoryService;


    /**
     * @description:
     * @return: 实现登录功能
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 16:01
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping("/carMessage")
    public String jumpCarMessage(ModelMap modelMap,HttpServletRequest request,@RequestParam(value = "index", defaultValue = "1") Integer index,@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        HttpSession session = request.getSession();
        String flag = "";
        if (session.getAttribute("keyword") != null) {
            keyword = (String)session.getAttribute("keyword");
            session.removeAttribute("keyword");
        }
        if (session.getAttribute("index") != null ){
            index = (Integer)session.getAttribute("index");
            session.removeAttribute("index");
        }
        if (session.getAttribute("flag") != null ){
            flag = (String) session.getAttribute("flag");
            session.removeAttribute("flag");
        }
        if (keyword.length() != 0 && ("car".equals(flag) || "".equals(flag))){
            PageInfo<CarMessages> carMessagesPageInfo = carMessagesService.selectLike(keyword, index);
            modelMap.addAttribute("keyword",keyword);
            modelMap.addAttribute("list",carMessagesPageInfo);
        }else {
            PageInfo<CarMessages> allCarMessage = carMessagesService.getAllCarMessage(index);
            modelMap.addAttribute("list",allCarMessage);
        }
        return "carMessage/carMessage";
    }

    /**
     * @description:
     * @return: 实现添加功能,车辆信息的添加必须由A,B,C操作
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 16:02
     */
    //前往添加页面
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/carMessageAdd")
    public String carMessageAdd(CarMessages carMessages, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Object success = session.getAttribute("success");
        if (success != null) {
            session.removeAttribute("success");
            model.addAttribute("success", success);
        }
        return "carMessage/carMessageAdd";
    }

    //添加数据
    @PermissionLimit(value = {"A","B","C"})
    @PostMapping(value = "/carMessage")
    public String saveCarMessage(CarMessages carMessages, ModelMap model, HttpServletRequest request,@RequestParam(value = "index",defaultValue = "1")Integer index ) {
        if (carMessagesService.getCarMessageById(carMessages.getCarId()) != null) {
            model.addAttribute("msg", "车辆信息已存在请检查填入信息");
            model.addAttribute("carMessages", carMessages);
            return "carMessage/carMessageAdd";
        } else {
            String inventoryName = inventoryService.selectInventoryName(carMessages.getCarName(), carMessages.getCarType(), carMessages.getCarBrand(), carMessages.getCarVersion(),carMessages.getCarProvide());

            if (inventoryName.length() != 0) {
                carMessages.setCarStockId(inventoryName);
                inventoryService.updateCountAdd(inventoryName,carMessages);
            }else {
                carMessages.setCarStockId(Util.generateInventoryName(inventoryService));
                inventoryService.insertInventory(carMessages);
            }
            carMessagesService.saveCarMessage(carMessages);
            HttpSession session = request.getSession();
            session.setAttribute("success", "入库成功");
            session.setAttribute("flag", "car");
            session.setAttribute("keyword", carMessages.getCarId());
            return "redirect:/carMessageAdd";
        }
    }

    @PermissionLimit(value = {"A","B","C"})
    //添加数据完成之后的跳转到添加的数据的位置
    @GetMapping(value = "/carMessage/max")
    public String jumpCompleteAddCarMessage(HttpServletRequest request) {
        return "redirect:/carMessage";
    }

    /**
     * @description:
     * @return: 删除数据
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 19:49
     */
    //删除数据库中车辆的数据
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/carMessage/delete")
    public String jumpDeleteCarMessage(HttpServletRequest request, @RequestParam(value = "keyword") String keyword, @RequestParam(value = "id") String id, @RequestParam(value = "index", defaultValue = "1") Integer index) {
        CarMessages carMessages = carMessagesService.getCarMessageById(id);
        carMessagesService.deleteCarMessage(id);
        String inventoryName = carMessages.getCarStockId();
        inventoryService.updateCountSubtract(inventoryName);
        HttpSession session = request.getSession();
        session.setAttribute("index", index);
        session.setAttribute("flag", "car");
        session.setAttribute("keyword", keyword);
        return "redirect:/carMessage";
    }

    /**
     * @description:
     * @return: 修改数据库中车辆的的数据
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 22:49
     */
    //修改数据库中的车辆信息
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/carMessage/update")
    public String jumpUpdate(ModelMap model, HttpServletRequest request, @RequestParam(value = "keyword") String keyword, @RequestParam(value = "id") String id, @RequestParam(value = "index", defaultValue = "1") Integer index) {
        CarMessages carMessage = carMessagesService.getCarMessageById(id);
        model.addAttribute("carMessage", carMessage);
        HttpSession session = request.getSession();
        session.setAttribute("flag", "car");
        session.setAttribute("keyword", keyword);
        session.setAttribute("id", id);
        session.setAttribute("index", index);
        return "carMessage/carMessageModification";
    }

    @PermissionLimit(value = {"A","B","C"})
    @PostMapping(value = "/carMessageModification")
    public String modificationCarMessage(HttpServletRequest request, CarMessages carMessages, Model model) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String inventoryName = inventoryService.selectInventoryName(carMessages.getCarName(), carMessages.getCarType(), carMessages.getCarBrand(), carMessages.getCarVersion(),carMessages.getCarProvide());
        if (carMessages.getCarStockId() != inventoryName){
            if (inventoryName.length() != 0) {
                carMessages.setCarStockId(inventoryName);
                inventoryService.updateCountAdd(inventoryName,carMessages);
            }else {
                carMessages.setCarStockId(Util.generateInventoryName(inventoryService));
                inventoryService.insertInventory(carMessages);
            }
        }
        if (id.equals(carMessages.getCarId())) {
            carMessagesService.updateCarMessage(id, carMessages);
        } else if (carMessagesService.getCarMessageById(carMessages.getCarId()) == null) {
            carMessagesService.updateCarMessage(id, carMessages);
        } else {
            model.addAttribute("carMessage", carMessages);
            model.addAttribute("msg", "修改失败，可能该车辆的已存在，请仔细核对");
            return "carMessage/update";
        }
        return "redirect:/carMessage";
    }
}
