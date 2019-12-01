package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.tl.ssm.domain.Member;
import com.tl.ssm.domain.Orders;
import com.tl.ssm.service.MemberService;
import com.tl.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrdersService ordersService;

    //查询所有会员信息 分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "6") Integer size
    ) throws Exception {
        ModelAndView view = new ModelAndView();
        List<Member> memberList = memberService.findAll(page, size);
        //分页bean
        PageInfo pageInfo = new PageInfo(memberList);
        //总共有多少页数据
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if ((pageInfo.getTotal()%pageInfo.getPageSize())!=0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }

        view.addObject("pageInfo",pageInfo);
        view.setViewName("member-list");
        return view;
    }

    //删除
    @RequestMapping("/delete.do")
    @ResponseBody //该方法的返回结果直接写到Http response Body中，常用在ajax异步请求中，
    public String delete(Integer id) throws Exception {
        String str = "";
        //根据member的id查询订单是否有跟member关联
        Orders byMemberId = ordersService.findByMemberId(id);
        System.out.println(byMemberId+"MemBer");
        //如果没有该对象则进行删除操作
        if (byMemberId==null){
            memberService.deleteById(id);
            str = "OK";
        }else {
            str = "Failed";
        }
        System.out.println(id+"Mem");
        return str;
    }

    //新建会员
    @RequestMapping("/save.do")
    public String save(Member member) throws Exception {
        memberService.save(member);
        return "redirect:findAll.do";
    }

}
