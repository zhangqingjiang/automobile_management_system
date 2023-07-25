package com.zhang.automobile_management_system.service.clientService.clientServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.automobile_management_system.bean.CarOrder;
import com.zhang.automobile_management_system.bean.Client;
import com.zhang.automobile_management_system.bean.ClientExample;
import com.zhang.automobile_management_system.service.BaseService;
import com.zhang.automobile_management_system.service.clientService.ClientService;
import com.zhang.automobile_management_system.service.orderService.OrderService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/1 14:42
 */
@Service
public class ClientMessageServiceImpl extends BaseService implements ClientService {

    @Autowired
    OrderService orderService;

    @Override
    public int insertClient(Client client) {
        int insert = clientMapper.insert(client);
        return insert;
    }

    @Override
    public int deleteClient(String id) {
        int delete = clientMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public int updateClient(String id, Client client) {
        ClientExample.Criteria criteria = clientExample.createCriteria();
        criteria.andClientIdEqualTo(id);
        int update = clientMapper.updateByExampleSelective(client, clientExample);
        clientExample.clear();
        return update;
    }

    @Override
    public Client selectClient(String id) {
        Client client = clientMapper.selectByPrimaryKey(id);
        return client;
    }

    @Override
    public PageInfo<Client> getAllClient(Integer index) {
        PageHelper.startPage(index, 10);
        List<Client> clients = clientMapper.selectByExample(null);
        return new PageInfo<>(clients,5);
    }

    /**
     * @description:
     * @return: 根据员工Id查询自己服务过的用户
     * @param:
     * @author ZQJ
     * @date: 2023/4/15 18:08
     */
    @Override
    public PageInfo<Client> getAllClient(Integer index, String id) {
        PageHelper.startPage(index, 10);
        PageInfo<CarOrder> carOrderPageInfo = orderService.selectOrderLike(id, index);
        List<Client> clients = new ArrayList<>();
        List<CarOrder> list = carOrderPageInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            clients.add(clientMapper.selectByPrimaryKey(list.get(i).getOrderClientId()));
        }
        return new PageInfo<>(clients,5);
    }

    /**
     * @description:
     * @return: 通过关键词模糊查询信息
     * @param:
     * @author ZQJ
     * @date: 2023/4/10 19:00
     */
    @Override
    public PageInfo<Client> selectClientLike(String keyword, Integer index) {
        PageHelper.startPage(index, 10);
        clientExample.createCriteria().andClientIdLike(keyword);
        clientExample.or().andClientNameLike(keyword);
        clientExample.or().andClientPhoneLike(keyword);
        clientExample.or().andClientSexLike(keyword);
        List<Client> clients = clientMapper.selectByExample(clientExample);
        clientExample.clear();
        return new PageInfo<>(clients,5);
    }

    @Override
    public PageInfo<Client> selectClientLike(String keyword, Integer index, String id) {
        PageHelper.startPage(index, 10);
        //查询员工为id的服务过的所有信息
        PageInfo<CarOrder> carOrderPageInfo = orderService.selectOrderLike(keyword,id, index);
        List<Client> clients = new ArrayList<>();
        List<CarOrder> list = carOrderPageInfo.getList();
        for (int i = 0; i < list.size(); i++) {
            clients.add(clientMapper.selectByPrimaryKey(list.get(i).getOrderClientId()));
        }
        return new PageInfo<>(clients,5);
    }

    @Override
    public List<Client> selectClientByPhone(String phone) {
        clientExample.createCriteria().andClientPhoneEqualTo(phone);
        List<Client> clients = clientMapper.selectByExample(clientExample);
        clientExample.clear();
        return clients;
    }

    @Override
    public Integer selectClientByDate(String format) {
        Integer count = 0;
        List<Client> clients = clientMapper.selectByExample(null);
        for (int i = 0; i < clients.size(); i++) {
            if(clients.get(i).getClientId().indexOf(format) != -1){
                count ++;
            }
        }
        return count;
    }

}
