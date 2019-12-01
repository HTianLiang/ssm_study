package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import com.tl.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //角色查询  分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "4") Integer size
    ) throws Exception {
        ModelAndView view = new ModelAndView();
        List<Role> roles = roleService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(roles);
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }
        view.addObject("pageInfo",pageInfo);
        view.setViewName("role-list");
        return view;
    }

    //角色添加
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //查询角色以及角色可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findRoleByIdAndAllPermission(
            @RequestParam(name = "id",required = true) Integer roleId) throws Exception {

        ModelAndView view = new ModelAndView();
        //根据角色id查询角色
        Role role = roleService.findById(roleId);
        //根据角色id查询可以添加的权限
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        view.addObject("role",role);
        view.addObject("permissionList",permissionList);
        view.setViewName("role-permission-add");
        return view;
    }

    //角色详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        ModelAndView view = new ModelAndView();
        Role role = roleService.findById(id);
        view.addObject("role",role);
        view.setViewName("role-show");
        return view;
    }

    //添加权限
    @RequestMapping("/addPermissionToRole.do")
    @RolesAllowed("ADMIN")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) Integer roleId,
                                      @RequestParam(name = "ids",required = true) Integer[] permissionIds) throws Exception {

        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }

    //删除角色
    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(Integer id){
        roleService.deleteById(id);
        return "OK";
    }

}
