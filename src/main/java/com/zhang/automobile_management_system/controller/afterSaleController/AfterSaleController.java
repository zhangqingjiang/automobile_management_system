package com.zhang.automobile_management_system.controller.afterSaleController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.AfterSale;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.afterSaleService.AfterSaleService;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;
import com.zhang.automobile_management_system.service.clientService.ClientService;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @description:
 * @throws
 * @date 2023/4/10 22:12
 */
@Controller
public class AfterSaleController {


    @Autowired
    AfterSaleService afterSaleService;
    @Autowired
    ClientService clientService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CarMessagesService carMessagesService;

    /**
     * @description:
     * @return: 查询售后服务信息功能
     * @param:
     * @author ZQJ
     * @date: 2023/4/11 0:00
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping(value = "/afterSaleMessage")
    public String jumpAfterSaleMessage(ModelMap modelMap, HttpServletRequest request, @RequestParam(value = "index", defaultValue = "1") Integer index, @RequestParam(value = "keyword", defaultValue = "") String keyword){
        HttpSession session = request.getSession();
        Integer permission = (Integer)session.getAttribute("permission");
        Employee employee = (Employee)session.getAttribute("employee");
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
        if (permission == 5){
            if (keyword.length() != 0 && "afterSale".equals(flag)){
                PageInfo<AfterSale> afterSalePageInfo = afterSaleService.selectAfterSaleLike(index, keyword,employee.getEmployeeId());
                modelMap.addAttribute("keyword",keyword);
                modelMap.addAttribute("list",afterSalePageInfo);
            }else {
                PageInfo<AfterSale> afterSalePageInfo = afterSaleService.selectAllAfterSale(index,employee.getEmployeeId());
                modelMap.addAttribute("list",afterSalePageInfo);
            }
        }else {
            if (keyword.length() != 0 && "afterSale".equals(flag)){
                PageInfo<AfterSale> afterSalePageInfo = afterSaleService.selectAfterSaleLike(index, keyword);
                modelMap.addAttribute("keyword",keyword);
                modelMap.addAttribute("list",afterSalePageInfo);
            }else {
                PageInfo<AfterSale> afterSalePageInfo = afterSaleService.selectAllAfterSale(index);
                modelMap.addAttribute("list",afterSalePageInfo);
            }
        }
        return "afterSaleMessage/afterSaleMessage";
    }

    /**
     * @description:
     * @return:添加售后服务功能
     * @param:
     * @author ZQJ
     * @date: 2023/4/11 0:00
     */
    @PermissionLimit(value = {"A","B","C","D"})
    @GetMapping(value = "/afterSaleMessageAdd")
    public String afterSaleMessageAdd(AfterSale afterSale, HttpServletRequest request, ModelMap model){
        HttpSession session = request.getSession();
        Object success = session.getAttribute("success");
        if (success != null) {
            session.removeAttribute("success");
            model.addAttribute("success", success);
        }
        return "afterSaleMessage/afterSaleMessageAdd";
    }

    //添加数据
    @PermissionLimit(value = {"A","B","C","D"})
    @PostMapping(value = "/afterSaleMessageAdd")
    public String insertAfterSale(AfterSale afterSale, ModelMap model, HttpServletRequest request,@RequestParam(value = "index",defaultValue = "1")Integer index ) {
        if (clientService.selectClientByPhone(afterSale.getAfterSaleClientPhone()).size() == 0) {
            model.addAttribute("msg", "客户的电话可能不存在请与客户沟通");
            model.addAttribute("afterSale", afterSale);
            return "afterSaleMessage/afterSaleMessageAdd";
        } else if(employeeService.selectEmployee(afterSale.getAfterSaleEmployeeId()) == null) {
            model.addAttribute("msg","售后服务的员工编号未查询到请仔细检查");
            model.addAttribute("afterSale", afterSale);
            return "afterSaleMessage/afterSaleMessageAdd";
        }else {
            long date = new Date().getTime();
            String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
            afterSale.setAfterSaleDate(format);
            afterSale.setAfterSaleClientId(clientService.selectClientByPhone(afterSale.getAfterSaleClientPhone()).get(0).getClientId());


            afterSaleService.saveAfterSale(afterSale);
            HttpSession session = request.getSession();
            session.setAttribute("success", "保存成功");
            session.setAttribute("flag", "afterSale");
            session.setAttribute("keyword", format);
            return "redirect:/afterSaleMessageAdd";
        }
    }
    @PermissionLimit(value = {"A","B","C"})
    //添加数据完成之后的跳转到添加的数据的位置
    @GetMapping(value = "/afterMessage/max")
    public String jumpCompleteAddCarMessage(HttpServletRequest request) {
        return "redirect:/afterSaleMessage";
    }

    
    /**
     * @description: 
     * @return: 修改订单状态
     * @param: 
     * @author ZQJ
     * @date: 2023/4/13 17:16
     */
    @PermissionLimit(value = {"A","B","C","D"})
    @GetMapping(value = "/afterSaleUpdate")
    public String updateAfterSaleState(
                                        @RequestParam(value = "state")String state,
                                        @RequestParam("afterSaleDate")String date,
                                        @RequestParam("afterSaleClientId")String clientId,
                                       HttpServletRequest request,
                                       @RequestParam(value = "keyword",defaultValue = "")String keyword,
                                       @RequestParam(value = "index",defaultValue = "1")Integer index){

        HttpSession session = request.getSession();
        AfterSale afterSale = afterSaleService.selectAfterSaleByIdAndDate(date, clientId);
        if (afterSale != null){
            afterSale.setAfterSaleState(state);
            afterSaleService.updateAfterSale(afterSale.getAfterSaleId(), afterSale);
        }
        session.setAttribute("flag", "order");
        session.setAttribute("keyword", keyword);
        session.setAttribute("index", index);
        return "redirect:/afterSaleMessage";
    }
}
