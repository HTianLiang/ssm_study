package com.tl.ssm.dao;

import com.tl.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {
    //根据订单id查询旅客id --> 根据旅客id查询旅客信息
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findByOrdersId(Integer ordersId) throws Exception;

    //新增旅客
    @Insert("insert into traveller(name,sex,phoneNum,credentialsType,credentialsNum,travellerType) " +
            "values(#{name},#{sex},#{phoneNum},#{credentialsType},#{credentialsNum},#{travellerType})")
    void save(Traveller traveller) throws Exception;

    //查询所有没有与订单表关联的旅客
    @Select("select * from traveller where id not in (select travellerId from order_traveller)")
    List<Traveller> findOtherTraveller() throws Exception;

    //根据旅客id查询旅客
    @Select("select * from traveller where id = #{id}")
    Traveller findById(Integer id) throws Exception;
}
