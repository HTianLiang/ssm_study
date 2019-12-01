package com.tl.ssm.dao;

import com.tl.ssm.domain.Role;
import com.tl.ssm.domain.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    //根据用户名查询用户
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.tl.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public Users findByUserName(String username) throws Exception;

    //查询所有用户
    @Select("select * from users")
    public List<Users> findAll() throws Exception;

    //用户添加
    @Select("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(Users users) throws Exception;

    //用户详情
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.tl.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public Users findById(Integer id) throws Exception;

    //根据用户id查询可以添加的角色
    @Select("select * from role where id not in (select roleId from users_role where usersId = #{userId})")
    List<Role> findOtherRoles(Integer userId);

    //给用户添加角色
    //@Param 给参数Integer userId命名为 userId,与sql语句的values中的值对应
    @Insert("insert into users_role(usersId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    //根据id删除用户
    @Delete("delete from users where id = #{id}")
    void deleteUserById(Integer id);

    //根据id修改状态
    @Update("update users set status = '1' where id = #{id}")
    void updateOpenById(Integer id);
    @Update("update users set status = '0' where id = #{id}")
    void updateCloseById(Integer id);

    //根据id显示编辑用户信息
    @Select("select * from users where id = #{id}")
    Users editById(Integer id);

    //保存修改
    @Update("update users set email = #{email},username = #{username},phoneNum = #{phoneNum},status = #{status} where id = #{id}")
    void update(Users users) throws Exception;
}
