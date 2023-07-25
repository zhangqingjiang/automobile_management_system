package com.zhang.automobile_management_system.service;

import com.zhang.automobile_management_system.bean.*;
import com.zhang.automobile_management_system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZQJ
 * @version 1.0
 * @description:作为所有Service类必须实现的类，提取了所有Service类需要的Mapper和Beam
 * @throws
 * @date 2023/3/30 19:11
 */


public abstract class BaseService {

    @Autowired
    public CarMessagesSaleMapper carMessagesSaleMapper;
    @Autowired
    public CarMessagesSaleExample carMessagesSaleExample;

    @Autowired
    public AfterSaleMapper afterSaleMapper;
    @Autowired
    public AfterSaleExample afterSaleExample;

    @Autowired
    public InventoryMapper inventoryMapper;
    @Autowired
    public InventoryExample inventoryExample;

    @Autowired
    public CarMessagesMapper carMessagesMapper;
    @Autowired
    public CarMessagesExample carMessagesExample;

    @Autowired
    public CarOrderMapper carOrderMapper;
    @Autowired
    public CarOrderExample carOrderExample;

    @Autowired
    public ClientMapper clientMapper;
    @Autowired
    public ClientExample clientExample;

    @Autowired
    public EmployeeMapper employeeMapper;
    @Autowired
    public EmployeeExample employeeExample;

    @Autowired
    public  AnnouncementMapper announcementMapper;
    @Autowired
    public AnnouncementExample announcementExample;
}
