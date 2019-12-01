package com.tl.ssm.service;

import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    //查询角色  分页
    List<Role> findAll(int page,int pageSize) throws Exception;
    //角色添加
    void save(Role role) throws Exception;
    //根据角色id查询角色
    Role findById(Integer roleId) throws Exception;
    //跟据角色id查询可以添加的权限
    List<Permission> findOtherPermission(Integer roleId) throws Exception;
    //添加权限
    void addPermissionToRole(Integer roleId,Integer[] permissionIds) throws Exception;
    //根据id删除角色
    void deleteById(Integer id);
}
