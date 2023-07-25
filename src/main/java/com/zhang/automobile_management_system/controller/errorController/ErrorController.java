package com.zhang.automobile_management_system.controller.errorController;

import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/17 17:52
 */
@Controller
public class ErrorController {
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping(value = "/error.html")
    public String jumpError(){
        return "/error";
    }

}
