package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.tl.ssm.domain.Permission;
import com.tl.ssm.domain.Role;
import com.tl.ssm.service.PermissionService;
import com.tl.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    //查询资源权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "4") Integer size
    ) throws Exception {
        ModelAndView view = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissionList);
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }
        view.addObject("pageInfo",pageInfo);
        view.setViewName("permission-list");
        return view;
    }

    //添加资源权限
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //资源权限详情——使用角色详情的方法展示
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        ModelAndView view = new ModelAndView();
        //调用角色详情方法
        Role role = roleService.findById(id);
        view.addObject("role",role);
        view.setViewName("permission-show");
        return view;
    }

    //查询资源权限以及资源权限可以添加的角色
    @RequestMapping("/findPermissionByIdAndAllRole.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) Integer permissionId) throws Exception {
        ModelAndView view = new ModelAndView();
        //根据资源权限id查询资源权限
        Permission permission = permissionService.findById(permissionId);
        //根据资源权限id查询可以添加的角色
        List<Role> roleList = permissionService.findOtherRoles(permissionId);

        view.addObject("permission",permission);
        view.addObject("roleList",roleList);
        view.setViewName("permission-role-add");
        return view;
    }

    //给资源权限添加角色
    @RequestMapping("/addRoleToPermission.do")
    public String addRoleToUser(@RequestParam(name = "permissionId",required = true) Integer permissionId,
                                @RequestParam(name = "ids",required = true) Integer[] roleIds) throws Exception {

        permissionService.addRoleToPermission(permissionId,roleIds);
        return "redirect:findAll.do";
    }

}
