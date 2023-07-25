package com.zhang.automobile_management_system.controller.systemMessageController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.Announcement;
import com.zhang.automobile_management_system.mapper.AnnouncementMapper;
import com.zhang.automobile_management_system.service.systemMessage.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/1 0:16
 */
@Controller
public class SystemMassageController {

    @Autowired
    SystemMessage systemMessage;
    @Autowired
    Announcement announcement;
    /**
     * @description: 
     * @return: 系统公告的展示，都可见
     * @param: 
     * @author ZQJ
     * @date: 2023/4/14 22:06
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping(value = "/system_message")
    public String jumpSystemMassage(ModelMap modelMap){
        PageInfo<Announcement> announcementPageInfo = systemMessage.selectAllAnnouncement();
        modelMap.addAttribute("list",announcementPageInfo);
        return "system_message/system_message";
    }

    /**
     * @description:
     * @return: 添加数据
     * @param:
     * @author ZQJ
     * @date: 2023/4/16 21:32
     */
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/system_message_add")
    public String addSystemMessage(String message){
        return "system_message/system_message_add";
    }

    @PermissionLimit(value = {"A","B","C"})
    @PostMapping(value = "/system_message_add")
    public String addMessage(@RequestParam(value = "message",defaultValue = "") String message,ModelMap modelMap){
        announcement.setAnnouncementMessage(message);
        int i = systemMessage.insertAnnouncement(announcement);
        return "redirect:/system_message";
    }

    /**
     * @description:
     * @return: 删除数据
     * @param:
     * @author ZQJ
     * @date: 2023/4/16 21:35
     */
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/system_message_delete")
    public String deleteSystemMessage(@RequestParam(value = "message",defaultValue = "") String message,ModelMap modelMap){
        announcement.setAnnouncementMessage(message);
        int i = systemMessage.deleteAllAnnouncement(announcement);
        return "redirect:/system_message";
    }
}
