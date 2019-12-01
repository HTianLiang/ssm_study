package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.UserDao;
import com.tl.ssm.domain.Role;
import com.tl.ssm.domain.Users;
import com.tl.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    //密码加密
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = userDao.findByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
       /* User user = new User(users.getUsername(),"{noop}"+users.getPassword(),getAuthority(users.getRoles()));*/
        User user = new User(users.getUsername(), users.getPassword(), users.getStatus() == 0 ? false : true,
                true, true, true, getAuthority(users.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    //查询所有用户
    @Override
    public List<Users> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        List<Users> users = userDao.findAll();
        return users;
    }

    //用户添加
    @Override
    public void save(Users users) throws Exception {
        //对用户的密码进行加密  或者使用BCryptPasswordEncoderUtils工具类进行加密
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        System.out.println("加密后的密码："+users.getPassword());
        userDao.save(users);
    }

    //用户详情
    @Override
    public Users findById(Integer id) throws Exception {
        return userDao.findById(id);
    }

    //根据用户id查询可以添加的角色
    @Override
    public List<Role> findOtherRoles(Integer userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    //给用户添加角色
    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception {
        for (Integer roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }

    //根据id删除用户
    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    //根据id修改状态
    @Override
    public void updateOpenById(Integer id) {
        userDao.updateOpenById(id);
    }
    @Override
    public void updateCloseById(Integer id) {
        userDao.updateCloseById(id);
    }

    //根据id显示编辑用户信息
    @Override
    public Users editById(Integer id) {
        return userDao.editById(id);
    }

    //保存修改
    @Override
    public void update(Users users) throws Exception {
        userDao.update(users);
    }
}
