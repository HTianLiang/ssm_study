package com.tl.ssm.dao;

import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where usersId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",
                    javaType = java.util.List.class,many = @Many(select = "com.tl.ssm.dao.PermissionDao.findByRoleId"))
    })
    public List<Role> findRoleByUserId(Integer userId) throws Exception;

    //查询角色
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    //角色添加
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    //根据角色id查询角色 根据角色id查询出所有对应的权限信息
    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",
                    javaType = java.util.List.class,many = @Many(select = "com.tl.ssm.dao.PermissionDao.findByRoleId"))
    })
    public Role findById(Integer roleId) throws Exception;

    //跟据角色id查询可以添加的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(Integer roleId) throws Exception;

    //添加权限
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId) throws Exception;

    //根据id删除角色
    @Delete("delete from role where id = #{id}")
    void deleteById(Integer id);
}
