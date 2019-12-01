package com.tl.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.tl.ssm.dao.PermissionDao;
import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import com.tl.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    //查询资源权限 分页
    @Override
    public List<Permission> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        List<Permission> permissionList = permissionDao.findAll();
        return permissionList;
    }

    //添加资源权限
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    //根据资源权限id查询资源权限
    @Override
    public Permission findById(Integer permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }

    //根据资源权限id查询可以添加的角色
    @Override
    public List<Role> findOtherRoles(Integer permissionId) {
        return permissionDao.findOtherRoles(permissionId);
    }

    //给资源权限添加角色
    @Override
    public void addRoleToPermission(Integer permissionId, Integer[] roleIds) {
        for (Integer roleId:roleIds){
            permissionDao.addRoleToPermission(permissionId,roleId);
        }
    }
}
