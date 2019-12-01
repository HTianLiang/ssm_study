package com.tl.ssm.service;

import com.tl.ssm.domain.Member;

import java.util.List;

public interface MemberService {
    //查询所有会员信息 分页
    List<Member> findAll(Integer page,Integer size) throws Exception;
    //删除
    void deleteById(Integer id) throws Exception;
    //保存
    void save(Member member) throws Exception;
    //根据id查询会员
    Member findById(Integer id) throws Exception;
}
