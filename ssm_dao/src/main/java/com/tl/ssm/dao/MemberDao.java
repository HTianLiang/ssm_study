package com.tl.ssm.dao;

import com.tl.ssm.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberDao {
    //根据id查询会员信息
    @Select("select * from member where id = #{id}")
    public Member findById(Integer id) throws Exception;
    //查询所有会员信息
    @Select("select * from member")
    List<Member> findAll() throws  Exception;
    //删除
    @Delete("delete from member where id = #{id}")
    void deleteById(Integer id) throws Exception;
    //保存
    @Insert("insert into member(name,nickname,phoneNum,email) values(#{name},#{nickname},#{phoneNum},#{email})")
    void save(Member member) throws Exception;
}
