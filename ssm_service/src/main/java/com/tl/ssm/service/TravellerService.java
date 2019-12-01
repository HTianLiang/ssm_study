package com.tl.ssm.service;

import com.tl.ssm.domain.Traveller;

import java.util.List;

public interface TravellerService {
    //新增旅客
    void save(Traveller traveller) throws Exception;
    //查询所有没有跟订单表关联的旅客
    List<Traveller> findOtherTraveller() throws Exception;
}
