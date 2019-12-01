package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.MemberDao;
import com.tl.ssm.domain.Member;
import com.tl.ssm.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    //查询所有
    @Override
    public List<Member> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        List<Member> memberList = memberDao.findAll();
        return memberList;
    }

    //根据id删除
    @Override
    public void deleteById(Integer id) throws Exception {
        memberDao.deleteById(id);
    }

    //保存
    @Override
    public void save(Member member) throws Exception {
        memberDao.save(member);
    }

    //根据id查询会员
    @Override
    public Member findById(Integer id) throws Exception {
        return memberDao.findById(id);
    }
}
