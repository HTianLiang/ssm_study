package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.SysLogDao;
import com.tl.ssm.domain.SysLog;
import com.tl.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    //保存日志信息
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    //查询所有日志信息
    @Override
    public List<SysLog> findAll(int page,int pageSize) throws Exception {
        //page是页码值，pageSize代表每页显示条数
        PageHelper.startPage(page,pageSize);
        List<SysLog> sysLogs = sysLogDao.findAll();
        return sysLogs;
    }
}
