package com.zhang.automobile_management_system.controller.yamlModificationController;

import com.zhang.automobile_management_system.autoAnnotation.PermissionLimit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description:修改yaml参数的控制器
 * @throws
 * @date 2023/4/25 9:21
 */
@Controller
public class YamlModificationController {


    @PermissionLimit("A")
    @GetMapping("/updateSalary")
    public String YamlModificationController(@RequestParam("x2") BigDecimal x2,
                                             @RequestParam("x3") BigDecimal x3,
                                             @RequestParam("x4") BigDecimal x4,
                                             @RequestParam("x5") BigDecimal x5) throws FileNotFoundException {

        x2 = new BigDecimal(String.valueOf(x2)).setScale(5, BigDecimal.ROUND_HALF_UP);
        x3 = new BigDecimal(String.valueOf(x3)).setScale(5, BigDecimal.ROUND_HALF_UP);
        x4 = new BigDecimal(String.valueOf(x4)).setScale(5, BigDecimal.ROUND_HALF_UP);
        x5 = new BigDecimal(String.valueOf(x5)).setScale(5, BigDecimal.ROUND_HALF_UP);
        URL url = YamlModificationController.class.getClassLoader().getResource("application.yaml");
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        dumperOptions.setPrettyFlow(false);
        Yaml yaml = new Yaml(dumperOptions);
        Map map = (Map) yaml.load(new FileInputStream(url.getFile()));
        System.out.println("这是修改前："+map.get("x2"));


        map.put("x2",x2);
        System.out.println("这是修改后："+map.get("x2"));


        yaml.dump(map, new OutputStreamWriter(new FileOutputStream(url.getFile())));
        return "redirect:/my_message";
    }
}
