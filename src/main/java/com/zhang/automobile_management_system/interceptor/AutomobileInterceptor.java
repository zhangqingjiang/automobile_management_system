package com.zhang.automobile_management_system.interceptor;

import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 拦截器
 * @throws
 * @date 2023/4/13 19:27
 */

public class AutomobileInterceptor implements HandlerInterceptor {

    @Autowired
    EmployeeService employeeService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employee");
            if (employee != null) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                String[] method = {""};
                if (handlerMethod.getMethodAnnotation(PermissionLimit.class) != null) {
                    method = handlerMethod.getMethodAnnotation(PermissionLimit.class).value();
                }
                for (int i = 0; i < method.length; i++) {
                    if (employee.getEmployeePermission().equals(method[i])) {
                        return true;
                    }
                }
                response.sendRedirect("/error.html");
                return false;
            } else {
                session.setAttribute("msg", "请登录");
                response.sendRedirect("/login");
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            response.sendRedirect("/error.html");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
