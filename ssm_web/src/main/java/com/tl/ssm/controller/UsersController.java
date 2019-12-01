package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.tl.ssm.domain.Role;
import com.tl.ssm.domain.Users;
import com.tl.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    //查询所有用户  分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer pae,
            @RequestParam(name = "size",required = true,defaultValue = "4") Integer size
    ) throws Exception {
        ModelAndView view = new ModelAndView();
        List<Users> users = userService.findAll(pae,size);
        PageInfo pageInfo = new PageInfo(users);
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }
        view.addObject("pageInfo",pageInfo);
        view.setViewName("user-list");
        return view;
    }

    //用户添加
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")//具有ADMIN权限的用户才可以访问
    public String save(Users users) throws Exception {
        userService.save(users);
        return "redirect:findAll.do";
    }

    //用户详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        ModelAndView view = new ModelAndView();
        Users users = userService.findById(id);
        view.addObject("user",users);
        view.setViewName("user-show");
        return view;
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) Integer userId) throws Exception {
        ModelAndView view = new ModelAndView();

        //根据用户id查询用户
        Users users = userService.findById(userId);
        //根据用户id查询可以添加的角色
        List<Role> roleList = userService.findOtherRoles(userId);

        view.addObject("user",users);
        view.addObject("roleList",roleList);
        view.setViewName("user-role-add");
        return view;
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,
                                @RequestParam(name = "ids",required = true) Integer[] roleIds) throws Exception {

        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll.do";
    }

    //删除用户
    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(Integer id){
        userService.deleteUserById(id);
        return "OK";
    }

    //开启
    @RequestMapping("/open.do")
    @ResponseBody
    public String open(Integer id){
        userService.updateOpenById(id);
        return "OK";
    }

    //关闭
    @RequestMapping("/close.do")
    @ResponseBody
    public String close(Integer id){
        userService.updateCloseById(id);
        return "OK";
    }

    //用户编辑 根据id显示用户信息
    @RequestMapping("/editById.do")
    @RolesAllowed("ADMIN")
    public ModelAndView editById(@RequestParam(name = "id",required = true) Integer id){
        ModelAndView view = new ModelAndView();
        Users users = userService.editById(id);
        view.addObject("user",users);
        view.setViewName("user-edit");
        return view;
    }

    //保存修改
    @RequestMapping("/update.do")
    @RolesAllowed("ADMIN")
    public String update(Users users) throws Exception {
        userService.update(users);
        return "redirect:findAll.do";
    }

    //修改角色信息
    @RequestMapping("/editRoleByUserId.do")
    @RolesAllowed("ADMIN")
    public ModelAndView editRoleByUserId(){
        ModelAndView view = new ModelAndView();
        view.setViewName("user-role-edit");
        return view;
    }

}
