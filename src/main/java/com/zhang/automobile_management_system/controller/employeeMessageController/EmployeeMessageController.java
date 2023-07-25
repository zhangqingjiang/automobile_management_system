package com.zhang.automobile_management_system.controller.employeeMessageController;


import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
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

/**
 * @author ZQJ
 * @version 1.0
 * @description: 员工注册
 * @throws
 * @date 2023/3/31 0:21
 */
@Controller
public class EmployeeMessageController {

    @Autowired
    EmployeeService employeeService;

    //默认初始密码
    private static  String EMPLOYEE_PASSWORD = "123456";

    /**
     * @description:
     * @return: 实现全部查询功能，只有老板和经理，管理才能看到全部的员工信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 16:01
     */
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping("/employee")
    public String jumpEmployeeMessage(ModelMap modelMap, HttpServletRequest request, @RequestParam(value = "keyword",defaultValue = "")String keyword,@RequestParam(value = "index", defaultValue = "1") Integer index) {
        HttpSession session = request.getSession();
        Integer permission = (Integer)session.getAttribute("permission");
        String flag = "";
        if (session.getAttribute("keyword") != null) {
            keyword = (String)session.getAttribute("keyword");
            session.removeAttribute("keyword");
        }
        if (session.getAttribute("index") != null ){
            index = (Integer)session.getAttribute("index");
            session.removeAttribute("index");
        }
        if (session.getAttribute("flag") != null){
            flag = (String)session.getAttribute("flag");
        }
        if (keyword.length() != 0 && ("employee".equals(flag) || "".equals(flag))){
            PageInfo<Employee> employeePageInfo = employeeService.selectEmployeeLike(keyword, index);
            modelMap.addAttribute("keyword",keyword);
            modelMap.addAttribute("list",employeePageInfo);
        }else {
            PageInfo<Employee> employee = employeeService.selectAllEmployee(index);
            modelMap.addAttribute("list",employee);
        }
        return "employeeMessage/employee";
    }

    /**
     * @description:
     * @return: 添加员工，只有老板、经理和管理才能添加员工，而且只能添加低一级的员工
     * @param:
     * @author ZQJ
     * @date: 2023/4/5 16:36
     */
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "employeeRegistration")
    public String jumpEmployeeRegistration(Employee employee, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Object success = session.getAttribute("success");
        if (success != null) {
            session.removeAttribute("success");
            model.addAttribute("success", success);
        }
        return "employeeMessage/employeeRegistration";
    }
    @PermissionLimit(value = {"A","B","C"})
    @PostMapping(value = "employeeAdd")
    public String employeeRegistration(Employee employees, HttpServletRequest request, ModelMap model){

        HttpSession session = request.getSession();
        Integer permission = (Integer)session.getAttribute("permission");
        String limit = Util.limit(employees.getEmployeeRole());
        if (permission >= Util.getPermissionGrade(limit)) {
            return "redirect:/error.html";
        }
        if (employeeService.selectEmployee(employees.getEmployeeId()) != null){
            model.addAttribute("employee",employees);
            model.addAttribute("msg","该员工编号已经存在，请仔细核对");
            return "employeeMessage/employeeRegistration.html";
        } else if (employeeService.selectEmployeeByPhone(employees.getEmployeePhone()) != null){
            model.addAttribute("employee",employees);
            model.addAttribute("msg","该员工电话已经存在，请仔细核对");
            return "employeeMessage/employeeRegistration.html";
        }else {
            //初始化系统定义信息
            employees.setEmployeePermission(limit);
            employees.setEmployeeId(Util.generateId(employees.getEmployeeRole(),employeeService));
            employees.setEmployeePassword(EMPLOYEE_PASSWORD);
            employees.setEmployeeUsername(Util.generateId(employees.getEmployeeRole(),employeeService));
            employees.setEmployeeDate(Util.generateDate());

            employeeService.insertEmployee(employees);
            session.setAttribute("success", "注册成功成功");
            session.setAttribute("flag", "employee");
            session.setAttribute("keyword", employees.getEmployeeId());
            return "redirect:/employeeRegistration";
        }
    }

    //添加数据完成之后的跳转到添加的数据的位置
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/employeeMessage/max")
    public String jumpCompleteAddEmployeeMessage(Model model, HttpServletRequest request) {
        return "redirect:/employee";
    }

    /**
     * @description:
     * @return: 删除数据
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 19:49
     */
    //删除员工信息
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/employeeMessage/delete")
    public String jumpDeleteCarMessage(HttpServletRequest request, @RequestParam(value = "keyword") String keyword, @RequestParam(value = "id", defaultValue = "") String id, @RequestParam(value = "index", defaultValue = "1") Integer index) {
        employeeService.deleteEmployee(id);
        HttpSession session = request.getSession();
        session.setAttribute("keyword", keyword);
        session.setAttribute("flag", "employee");
        session.setAttribute("index", index);
        return "redirect:/employee";
    }

    /**
     * @description:
     * @return: 修改员工数据，只有经理和管理才能修改
     * @param:
     * @author ZQJ
     * @date: 2023/4/4 22:49
     */
    //跳转到修改员工信息页面
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/employeeMessage/update")
    public String jumpUpdate(ModelMap model,HttpServletRequest request, @RequestParam(value = "keyword") String keyword, @RequestParam(value = "id", defaultValue = "") String id, @RequestParam(value = "index", defaultValue = "1") Integer index) {
        Employee employee = employeeService.selectEmployee(id);
        model.addAttribute("employee",employee);
        HttpSession session = request.getSession();
        session.setAttribute("keyword", keyword);
        session.setAttribute("flag", "employee");
        session.setAttribute("id", id);
        session.setAttribute("index", index);
        return "employeeMessage/employeeMessageModification";
    }
    @PermissionLimit(value = {"A","B","C"})
    @PostMapping(value = "/employeeMessageModification")
    public String modificationCarMessage(HttpServletRequest request,Employee employee,Model model) {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        if (id.equals(employee.getEmployeeId())){
            employeeService.updateEmployee(id,employee);
        }else if(employeeService.selectEmployee(id) == null){
            employeeService.updateEmployee(id,employee);
        }else {
            model.addAttribute("employee", employee);
            model.addAttribute("msg", "修改失败，可能该员工已存在，请仔细核对");
            return "employeeMessage/update";
        }
        return "redirect:/employee";
    }
}
