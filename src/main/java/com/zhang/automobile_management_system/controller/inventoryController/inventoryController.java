package com.zhang.automobile_management_system.controller.inventoryController;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.Inventory;
import com.zhang.automobile_management_system.service.inventoryService.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Key;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 处理车辆入库后的显示问题
 * @throws
 * @date 2023/4/6 21:07
 */
@Controller
public class inventoryController {

    @Autowired
    InventoryService inventoryService;
    /**
     * @description:
     * @return: 跳转到车辆库存页面
     * @param:
     * @author ZQJ
     * @date: 2023/4/6 21:15
     */
    @PermissionLimit(value = {"A","B","C","D","E"})
    @GetMapping("/inventoryMessage")
    public String jumpInventory(ModelMap modelMap, HttpServletRequest request, @RequestParam(value = "index", defaultValue = "1") Integer index, @RequestParam(value = "keyword", defaultValue = "") String keyword){
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
            flag = (String)session.getAttribute("flag");
            session.removeAttribute("flag");
        }
        if (keyword.length() != 0 && ("inventory".equals(flag) || "".equals(flag))){
            PageInfo<Inventory> inventoryPageInfo = inventoryService.selectLike(keyword, index);
            modelMap.addAttribute("keyword",keyword);
            modelMap.addAttribute("list",inventoryPageInfo);
        }else {
            PageInfo<Inventory> inventoryPageInfo = inventoryService.selectAllMessage(index);
            modelMap.addAttribute("list",inventoryPageInfo);
        }
        return "inventoryMessage/inventoryMessage";
    }

    /**
     * @description:
     * @return: 当库存为0时经理和管理有权选择是否删除此库
     * @param:
     * @author ZQJ
     * @date: 2023/4/14 21:46
     */
    @PermissionLimit(value = {"A","B","C"})
    @GetMapping(value = "/inventoryMessage/delete")
    public String delete(@RequestParam("id")String id, @RequestParam("keyword")String keyword,@RequestParam("index")Integer index,HttpServletRequest request){
        inventoryService.deleteInventory(id);
        HttpSession session = request.getSession();
        session.setAttribute("keyword", keyword);
        session.setAttribute("flag", "inventory");
        session.setAttribute("index", index);
        return "redirect:/inventoryMessage";
    }
}
