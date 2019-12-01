package com.tl.ssm.service;

import com.tl.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {
    /*//查询所有订单信息   未分页
    List<Orders> findAll() throws Exception;*/

    //分页查询
    List<Orders> findAll(int page,int pageSize) throws Exception;

    //查询订单详情
    Orders findById(Integer ordersId) throws Exception;

    //根据member的id查询订单是否有跟member关联
    Orders findByMemberId(Integer id) throws Exception;

    //新增订单
    void save(Orders orders,Integer memberId) throws Exception;

    //根据订单id查询订单
    Orders findByOrdersId(Integer id) throws Exception;

    //添加旅客 进行旅客与订单关联
    void addTravellerToOrders(Integer ordersId,Integer[] travellerIds) throws Exception;

    //关联产品
    void addProductToProduct(Integer ordersId,Integer[] productId) throws Exception;
}
