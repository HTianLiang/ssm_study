package com.tl.ssm.service;

import com.tl.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {
    //保存日志信息
    void save(SysLog sysLog) throws Exception;
    //查询所有日志信息 —— 分页
    List<SysLog> findAll(int page,int pageSize) throws Exception;
}
