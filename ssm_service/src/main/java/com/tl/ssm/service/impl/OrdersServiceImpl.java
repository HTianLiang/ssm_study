package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.OrdersDao;
import com.tl.ssm.domain.Orders;
import com.tl.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    /*//查询所有订单信息 未分页
    @Override
    public List<Orders> findAll() throws Exception {
        List<Orders> orders = ordersDao.findAll();
        return orders;
    }*/

    //分页查询
    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {
        //page是页码值，pageSize代表每页显示条数
        PageHelper.startPage(page,pageSize);
        List<Orders> orders = ordersDao.findAll();
        return orders;
    }

    //订单详情查询
    @Override
    public Orders findById(Integer ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }

    //在orders中根据member的id查询订单是否有跟member关联
    @Override
    public Orders findByMemberId(Integer id) throws Exception {
        return ordersDao.findByMemberId(id);
    }

    //新增订单
    @Override
    public void save(Orders orders,Integer memberId) throws Exception {
        ordersDao.save(orders,memberId);
    }

    //根据订单id查询订单
    @Override
    public Orders findByOrdersId(Integer id) throws Exception {
        return ordersDao.findByOrdersId(id);
    }

    //添加旅客 进行旅客与订单关联
    @Override
    public void addTravellerToOrders(Integer ordersId, Integer[] travellerIds) throws Exception {
        for (Integer travellerId:travellerIds){
            ordersDao.addTravellerToOrders(ordersId,travellerId);
        }
    }

    //关联产品
    @Override
    public void addProductToProduct(Integer ordersId, Integer[] productId) throws Exception {
        for (Integer productIds:productId){
            ordersDao.addProductToProduct(ordersId,productIds);
        }
    }

}
