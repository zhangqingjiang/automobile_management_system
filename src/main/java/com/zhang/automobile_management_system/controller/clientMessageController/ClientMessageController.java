package com.zhang.automobile_management_system.controller.clientMessageController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarOrder;
import com.zhang.automobile_management_system.bean.Client;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.clientService.ClientService;
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
 * @description:
 * @throws
 * @date 2023/4/1 14:27
 */
@Controller
public class ClientMessageController {
    @Autowired
    ClientService clientService;

    /**
     * @description:
     * @return: 查询用户信息,都可以查询，但是普通员工只能查询自己服务过的用户信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/7 22:48
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping(value = "/clientMessage")
    public String jumpClientMessage(ModelMap modelMap, HttpServletRequest request, @RequestParam(value = "index", defaultValue = "1") Integer index, @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        HttpSession session = request.getSession();
        Integer permission = (Integer)session.getAttribute("permission");
        Employee employee = (Employee)session.getAttribute("employee");

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
        if (permission == 5){
            if (keyword.length() != 0 && "client".equals(flag)) {
                PageInfo<Client> clientPageInfo = clientService.selectClientLike(keyword, index,employee.getEmployeeId());
                modelMap.addAttribute("keyword", keyword);
                modelMap.addAttribute("list", clientPageInfo);
            } else {
                PageInfo<Client> allClient = clientService.getAllClient(index,employee.getEmployeeId());
                modelMap.addAttribute("list", allClient);
            }
        }else {
            if (keyword.length() != 0 && "client".equals(flag)) {
                PageInfo<Client> clientPageInfo = clientService.selectClientLike(keyword, index);
                modelMap.addAttribute("keyword", keyword);
                modelMap.addAttribute("list", clientPageInfo);
            } else {
                PageInfo<Client> allClient = clientService.getAllClient(index);
                modelMap.addAttribute("list", allClient);
            }
        }
        return "clientMessage/clientMessage";
    }

}
