package com.tl.ssm.service;


import com.tl.ssm.domain.Role;
import com.tl.ssm.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/*
    继承的UserDetailsService 实现权限验证登陆
 */
public interface UserService extends UserDetailsService {
    //查询所有用户
    List<Users> findAll(int page,int pageSize) throws Exception;
    //用户添加
    void save(Users users) throws Exception;
    //用户详情
    Users findById(Integer id) throws Exception;
    //根据用户id查询可以添加的角色
    List<Role> findOtherRoles(Integer userId) throws Exception;
    //给用户添加角色
    void addRoleToUser(Integer userId,Integer[] roleIds) throws Exception;

    //根据id删除用户
    void deleteUserById(Integer id);

    //根据id修改状态
    void updateOpenById(Integer id);
    void updateCloseById(Integer id);

    //根据id显示编辑用户信息
    Users editById(Integer id);

    //保存修改
    void update(Users users) throws Exception;
}
