package com.tl.ssm.service;

import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {
    //查询资源权限  分页
    List<Permission> findAll(int page,int size) throws Exception;
    //添加资源权限
    void save(Permission permission) throws Exception;
    //根据id查询资源权限
    Permission findById(Integer permissionId) throws Exception;
    //根据资源权限id查询可以添加的角色
    List<Role> findOtherRoles(Integer permissionId);
    //给资源权限添加角色
    void addRoleToPermission(Integer permissionId, Integer[] roleIds);
}
