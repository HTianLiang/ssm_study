package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.RoleDao;
import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import com.tl.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    //查询所有角色  分页
    @Override
    public List<Role> findAll(int page,int pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        List<Role> roles = roleDao.findAll();
        return roles;
    }

    //保存
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    //根据角色id查询角色
    @Override
    public Role findById(Integer roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    //根据角色id查询可以添加的权限
    @Override
    public List<Permission> findOtherPermission(Integer roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    //添加权限
    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception {
        for (Integer permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    //删除
    @Override
    public void deleteById(Integer id) {
        roleDao.deleteById(id);
    }
}
