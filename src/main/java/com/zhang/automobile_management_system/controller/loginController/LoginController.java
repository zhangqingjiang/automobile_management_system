package com.zhang.automobile_management_system.controller.loginController;

import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import com.zhang.automobile_management_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 实现对登录功能的处理
 * @throws
 * @date 2023/3/29 22:33
 */
@Controller
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping(value = {"/","/login"})
    public String login(HttpServletRequest request,ModelMap model){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("msg");
        if (msg != null){
            model.addAttribute("msg",msg);
            session.removeAttribute("msg");
        }
        return "/login.html";
    }

    @PermissionLimit(value = {"A","B","C","D","E"})
    @PostMapping(value = {"/login"})
    public String redirectCarMessages(@RequestParam(value = "username",defaultValue = "")String username,
                                      @RequestParam(value = "password",defaultValue = "")String password,
                                      ModelMap model,
                                      HttpServletRequest request){
        HttpSession session = request.getSession();
        Employee employee = employeeService.selectEmployee(username);
        if (employee != null){
            if (password.equals(employee.getEmployeePassword())){
                session.setAttribute("employee", employee);
                session.setAttribute("permission", Util.getPermissionGrade(employee.getEmployeePermission()));

                if ("123456".equals(password)){
                    return "redirect:/system_message";
                }
                return "redirect:/carMessage";

            }else {
                model.addAttribute("msg","密码不正确请仔细检查");
                model.addAttribute("username",username);
                return "/login";
            }
        }else {
            model.addAttribute("username",username);
            model.addAttribute("msg","账号不存在请仔细检查");
            return "/login";
        }
    }

}
