package com.tl.ssm.dao;

import com.tl.ssm.domain.Member;
import com.tl.ssm.domain.Orders;
import com.tl.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrdersDao {
    //查询所有订单信息 多表操作
    @Select("select * from orders")
    @Results({
            //id=true主键 id跟数据库的id对应
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(property = "product",column = "productId",
                    //指定的类型  此方法可根据productId查询出product
                    javaType = Product.class,one = @One(select = "com.tl.ssm.dao.ProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;

    //查询订单详情信息 多表操作
    @Select("select * from orders where id = #{ordersId}")
    @Results({
            //id=true主键 id跟数据库的id对应
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(property = "product",column = "productId",
                    //指定的类型  此方法可根据productId查询出product
                    javaType = Product.class,one = @One(select = "com.tl.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",
                    javaType = Member.class,one = @One(select = "com.tl.ssm.dao.MemberDao.findById")),
            //多对多
            @Result(property = "travellers",column = "id",
                    javaType = java.util.List.class,many = @Many(select = "com.tl.ssm.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(Integer ordersId) throws Exception;

    //根据member的id查询订单是否有跟member关联
    @Select("select * from orders where memberId = #{id}")
    Orders findByMemberId(Integer id) throws Exception;

    //新增订单 及把member关联
    @Insert("insert into orders(orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,memberId) " +
            "values(#{orders.orderNum},#{orders.orderTime},#{orders.peopleCount},#{orders.orderDesc},#{orders.payType},#{orders.orderStatus},#{memberId})")
    void save(@Param("orders") Orders orders,@Param("memberId") Integer memberId) throws Exception;

    //根据订单id查询订单
    @Select("select * from orders where id = #{id}")
    Orders findByOrdersId(Integer id) throws Exception;

    //添加旅客 进行旅客与订单关联
    @Insert("insert into order_traveller(orderId,travellerId) values(#{ordersId},#{travellerIds})")
    void addTravellerToOrders(@Param("ordersId") Integer ordersId,@Param("travellerIds") Integer travellerIds) throws Exception;

    //关联产品
    @Update("update orders set productId = #{productId} where id = #{ordersId}")
    void addProductToProduct(@Param("ordersId") Integer ordersId,@Param("productId") Integer productId) throws Exception;
}
