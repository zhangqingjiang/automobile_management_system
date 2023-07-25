package com.zhang.automobile_management_system.controller.leaveContoller;

import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 退出本程序
 * @throws
 * @date 2023/4/12 17:17
 */
@Controller
public class LeaveController {

    /**
     * @description:
     * @return: 离开程序
     * @param:
     * @author ZQJ
     * @date: 2023/4/14 21:46
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping("/leave")
    public String leave(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        request.getSession().removeAttribute("permission");
        return "redirect:/login";
    }
}
