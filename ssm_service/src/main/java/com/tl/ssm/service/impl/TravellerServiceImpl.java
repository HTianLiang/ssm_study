package com.tl.ssm.service.impl;

import com.tl.ssm.dao.TravellerDao;
import com.tl.ssm.domain.Traveller;
import com.tl.ssm.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravellerServiceImpl implements TravellerService {

    @Autowired
    private TravellerDao travellerDao;

    //新增旅客
    @Override
    public void save(Traveller traveller) throws Exception {
       travellerDao.save(traveller);
    }

    //查询所有没有跟订单表关联的旅客
    @Override
    public List<Traveller> findOtherTraveller() throws Exception {
        return travellerDao.findOtherTraveller();
    }
}
