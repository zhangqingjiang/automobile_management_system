package com.zhang.automobile_management_system.service.employeeService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.Employee;
import lombok.experimental.ExtensionMethod;
import org.aopalliance.intercept.Interceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/3/31 20:49
 */
@Repository
public interface EmployeeService {

    //增
    int insertEmployee(Employee employee);
    //删
    int deleteEmployee(String id);
    //改
    int updateEmployee(String id,Employee employee);
    //查
    Employee selectEmployee(String id);

    PageInfo<Employee> selectAllEmployee(Integer index);

    PageInfo<Employee> selectEmployeeById(String id);

    PageInfo<Employee> selectEmployeeLike(String keyword, Integer index);

    Employee selectEmployeeByPhone(String phone);
    public List<Employee> selectEmployeeLike(String keyword);
}
