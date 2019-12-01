package com.tl.ssm.dao;

import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    //根据RoleId查询出资源权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId(Integer roleId) throws Exception;

    //查询资源权限
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    //添加资源权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    //根据id查询资源权限
    @Select("select * from permission where id = #{permissionId}")
    Permission findById(Integer permissionId) throws Exception;

    //根据资源权限id查询可以添加的角色
    @Select("select * from role where id not in (select roleId from role_permission where permissionId = #{permissionId})")
    List<Role> findOtherRoles(Integer permissionId);

    //给资源权限添加角色
    //@Param 给参数Integer permissionId permissionId,与sql语句的values中的值对应
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addRoleToPermission(@Param("permissionId") Integer permissionId, @Param("roleId") Integer roleId);


}
