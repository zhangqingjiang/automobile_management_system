package com.zhang.automobile_management_system.service.employeeService.employeeServiceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarMessages;
import com.zhang.automobile_management_system.bean.Employee;
import com.zhang.automobile_management_system.bean.EmployeeExample;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.employeeService.EmployeeService;
import com.zhang.automobile_management_system.util.Util;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/3/31 20:54
 */
@Service
public class EmployeeServiceImp extends BaseService implements EmployeeService {

    /**
     * @description:
     * @return: 保存员工
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 15:36
     */
    @Override
    public int insertEmployee(Employee employee) {
        int i = employeeMapper.insertSelective(employee);
        return i;
    }

    @Override
    public int deleteEmployee(String id) {
        int delete = employeeMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public int updateEmployee(String id, Employee employee) {
        employee.setEmployeeUsername(employee.getEmployeeId());
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmployeeIdEqualTo(id);
        int update = employeeMapper.updateByExampleSelective(employee, employeeExample);
        employeeExample.clear();
        return update;
    }

    @Override
    public Employee selectEmployee(String id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public PageInfo<Employee> selectAllEmployee(Integer index) {
        PageHelper.startPage(index,10);
        List<Employee> employees = employeeMapper.selectByExample(null);
        return new PageInfo<Employee>(employees,5);
    }

    @Override
    public PageInfo<Employee> selectEmployeeById(String id) {
        PageHelper.startPage(1, 10);
        employeeExample.createCriteria().andEmployeeIdEqualTo(id);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        employeeExample.clear();
        return new PageInfo<Employee>(employees,5);
    }

    @Override
    public PageInfo<Employee> selectEmployeeLike(String keyword, Integer index) {

        employeeExample.setDistinct(true);
        employeeExample.createCriteria().andEmployeeIdLike(keyword);
        employeeExample.or().andEmployeeNameLike(keyword);
        employeeExample.or().andEmployeePhoneLike(keyword);
        employeeExample.or().andEmployeeSexLike(keyword);
        employeeExample.or().andEmployeeRoleLike(keyword);
        employeeExample.or().andEmployeePermissionLike(keyword);
        employeeExample.or().andEmployeeSalaryIn(Util.extractMath(keyword));

        PageHelper.startPage(index, 10);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        employeeExample.clear();
        return new PageInfo<>(employees, 5);
    }
    @Override
    public List<Employee> selectEmployeeLike(String keyword) {

        employeeExample.setDistinct(true);
        employeeExample.createCriteria().andEmployeeDateLike(keyword+"%");
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        employeeExample.clear();
        return employees;
    }


    /**
     * @description:
     * @return: 通过电话查询员工
     * @param:
     * @author ZQJ
     * @date: 2023/4/12 17:34
     */
    @Override
    public Employee selectEmployeeByPhone(String phone) {
        employeeExample.createCriteria().andEmployeePhoneEqualTo(phone);
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        employeeExample.clear();
        if (employees.size() == 0){
            return null;
        }else
            return employees.get(0);
    }
}
