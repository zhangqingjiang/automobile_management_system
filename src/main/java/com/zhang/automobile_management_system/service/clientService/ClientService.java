package com.zhang.automobile_management_system.service.clientService;

import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.Client;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/1 14:39
 */
@Repository
public interface ClientService {

    //增
    int insertClient(Client client);
    //删
    int deleteClient(String id);
    //改
    int updateClient(String id,Client client);
    //查
    Client selectClient(String id);

    PageInfo<Client> getAllClient(Integer index);
    PageInfo<Client> getAllClient(Integer index,String id);
    PageInfo<Client> selectClientLike(String keyword,Integer index);
    PageInfo<Client> selectClientLike(String keyword,Integer index,String id);

    List<Client> selectClientByPhone(String phone);
    Integer selectClientByDate(String format);
}
