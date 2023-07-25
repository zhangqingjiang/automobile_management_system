package com.zhang.automobile_management_system.util;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.*;
import com.zhang.automobile_management_system.service.carMessageService.CarMessagesService;
import com.zhang.automobile_management_system.service.clientService.ClientService;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import com.zhang.automobile_management_system.service.employeeService.employeeServiceImp.EmployeeServiceImp;
import com.zhang.automobile_management_system.service.inventoryService.InventoryService;
import com.zhang.automobile_management_system.service.orderService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZQJ
 * @version 1.0
 * @description: 将字符串长度补至八位长度
 * @throws
 * @date 2023/3/31 18:17
 */
@Component
public class Util {


    /**
     * @description:
     * @return: 自动将一个数变为前面带0的七位数
     * @param:
     * @author ZQJ
     * @date: 2023/4/5 16:07
     */
    static String DEFEAT = "0000000";


    public static String AddSize(String number) {
        while (number.length() < DEFEAT.length()) {
            number = "0" + number;
        }
        return number;
    }

    //提取字符串中的数字
    public static List<BigDecimal> extractMath(String str) {
        List<BigDecimal> list = new ArrayList<>();
        // 声明正则，匹配数字（1个或多个）.数字（1个或多个）
        String regex = "\\d+\\.+\\d+";
        // Pattern的构造方法是私有的，不可以直接创建，通过静态方法compile创建Pattern对象，查看源代码发现compile直接调用了Pattern构造函数。
        Pattern pattern = Pattern.compile(regex);
        // 返回一个Matcher对象。Matcher类的构造方法也是私有的，不能随意创建，只能通过Pattern.matcher(CharSequence input)方法得到该类的实例。
        Matcher matcher = pattern.matcher(str);
        // 对目标字符串进行正则匹配，通过while可以多次执行find方法，获取多次的匹配结果，代码编写方式类似于iterator.next()。
        while (matcher.find()) {
            // group() 返回匹配到的字符串，结合find函数使用。
            String group = matcher.group();
            BigDecimal bigDecimal = new BigDecimal(group).setScale(2,BigDecimal.ROUND_HALF_UP);
            list.add(bigDecimal);
        }
        BigDecimal bigDecimal = new BigDecimal(0.00);
        list.add(bigDecimal);
        return list;
    }

    //提取字符串中的数字
    public static List<Integer> extractMathInteger(String str) {
        List<Integer> list = new ArrayList<>();
        // 声明正则，匹配数字（1个或多个）.数字（1个或多个）
        String regex = "\\d+";
        // Pattern的构造方法是私有的，不可以直接创建，通过静态方法compile创建Pattern对象，查看源代码发现compile直接调用了Pattern构造函数。
        Pattern pattern = Pattern.compile(regex);
        // 返回一个Matcher对象。Matcher类的构造方法也是私有的，不能随意创建，只能通过Pattern.matcher(CharSequence input)方法得到该类的实例。
        Matcher matcher = pattern.matcher(str);
        // 对目标字符串进行正则匹配，通过while可以多次执行find方法，获取多次的匹配结果，代码编写方式类似于iterator.next()。
        while (matcher.find()) {
            // group() 返回匹配到的字符串，结合find函数使用。
            String group = matcher.group();
            Integer integer = new Integer(group);
            list.add(integer);
        }
        Integer integer = new Integer(-1);
        list.add(integer);
        return list;
    }


    /**
     * @description:
     * @return: 判断权限
     * @param:
     * @author ZQJ
     * @date: 2023/4/5 17:38
     */
    public static String limit(String role) {
        if (role.equals("经理")) {
            return "B";
        } else if (role.equals("管理")) {
            return "C";
        } else if (role.equals("前台")){
            return "D";
        }else{
            return "E";
        }

    }

    /**
     * @description:
     * @return: 判断员工ID生成器
     * @param:
     * @author ZQJ
     * @date: 2023/4/5 16:09
     */
    public static String generateId(String role, EmployeeService employeeService) {
        String limit = limit(role);
        PageInfo<Employee> employeePageInfo = employeeService.selectAllEmployee(1);
        String total = String.valueOf(employeePageInfo.getTotal());
        String s = AddSize(total);
        return limit + s;
    }

    /**
     * @description:
     * @return: 车库Id生成器
     * @param:
     * @author ZQJ
     * @date: 2023/4/6 22:03
     */
    public static String generateInventoryName(InventoryService inventoryService) {
        PageInfo<Inventory> inventoryPageInfo = inventoryService.selectAllMessage(1);
        String total = String.valueOf(inventoryPageInfo.getTotal());
        String s = AddSize(total);
        return s;
    }
    
    /**
     * @description: 
     * @return: 将字符串转换为时间
     * @param: 
     * @author ZQJ
     * @date: 2023/4/7 21:37
     */
    public static  Date dateFormat(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
          Date parse = simpleDateFormat.parse(date);
          return parse;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return null;
        }
    }

    /**
     * @description:
     * @return: 订单生成器
     * @param:
     * @author ZQJ
     * @date: 2023/4/8 0:02
     */
    public static String generateDate(){
        long newTime = new Date().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(newTime);

        return format;
    }

    public static String generateLastDate(Integer integer){
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH,-integer);
        Date time = instance.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String format = simpleDateFormat.format(time);

        return format;
    }
    /**
     * @description:
     * @return: 客户ID生成器
     * @param:
     * @author ZQJ
     * @date: 2023/4/8 0:02
     */
    public static String generateClientId(ClientService clientService){
        long newTime = new Date().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String format = simpleDateFormat.format(newTime);
        Integer integer = clientService.selectClientByDate(format);
        return format + String.valueOf(integer);
    }

    public static Integer getPermissionGrade(String employeePermission) {

        if ("A".equals(employeePermission)){
            return 1;
        }else if("B".equals(employeePermission)){
            return 2;
        }else if("C".equals(employeePermission)){
            return 3;
        }else if("D".equals(employeePermission)){
            return 4;
        }else {
            return 5;
        }
    }

    /**
     * @description:
     * @return: 获取时间的日期
     * @param:
     * @author ZQJ
     * @date: 2023/4/16 18:33
     */
    public static String getThisDateMonth(Date  time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        Date date = calendar.getTime();
        String format = simpleDateFormat.format(date);
        return format;
    }
    public static String getLastDateMonth(Date  time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        Date date = calendar.getTime();
        String format = simpleDateFormat.format(date);
        return format;
    }


}
