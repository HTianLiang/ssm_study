package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tl.ssm.domain.*;
import com.tl.ssm.service.MemberService;
import com.tl.ssm.service.OrdersService;
import com.tl.ssm.service.ProductService;
import com.tl.ssm.service.TravellerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TravellerService travellerService;


    /*未分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView view = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        view.addObject("ordersList",ordersList);
        view.setViewName("orders-list");
        return view;
    }*/

    //分页查询所有订单信息
    /*Controller层中 @RequestParam的作用：@RequestParam(name = "page", required = true, defaultValue = "1") int page
     其中name/value 参数名 代表前端传的参数值 或请求地址的参数值required表示是否必须,默认为true
     defaultValue请求参数的默认值
    */
    @RequestMapping("/findAll.do")
    @RolesAllowed({"USER","ADMIN"})//具有ADMIN、USER任意一种权限都可以访问
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "6") Integer size) throws Exception {
        ModelAndView view = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo 一个分页Bean
        PageInfo pageInfo = new PageInfo(ordersList);
        //总共有多少页数据
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if ((pageInfo.getTotal()%pageInfo.getPageSize())!=0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }

        view.addObject("pageInfo",pageInfo);
        System.out.println("总共"+pageCount);
        view.setViewName("orders-page-list");
        return view;
    }

    //订单详情
    @RequestMapping("/findById.do")
    @RolesAllowed({"USER","ADMIN"})
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer ordersId) throws Exception {
        ModelAndView view = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        view.addObject("orders",orders);
        view.setViewName("orders-show");
        return view;
    }

    //订单编辑
    @RequestMapping("/findByIdEdit.do")
    @RolesAllowed({"USER","ADMIN"})
    public ModelAndView findByIdedit(@RequestParam(name = "id",required = true) Integer ordersId) throws Exception {
        ModelAndView view = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        view.addObject("orders",orders);
        view.setViewName("orders-edit");
        return view;
    }

    //选择产品
    @RequestMapping("/selectProduct.do")
    public ModelAndView selectProduct(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "8") Integer size) throws Exception {

        ModelAndView view = new ModelAndView();
        List<Product> productList = productService.findAll(page,size);

        PageInfo pageInfo = new PageInfo(productList);
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }
        view.addObject("pageInfo",pageInfo);

        view.addObject("productList",productList);
        view.setViewName("select-product");
        return view;
    }

    //根据产品id查询产品信息给新建订单界面
    @RequestMapping("/getProductById.do")
    public ModelAndView getProductById(@RequestParam(name = "pid",required = true) Integer id) throws Exception {
        System.out.println("PRODUCT:"+id);
        ModelAndView view = new ModelAndView();
        Product product = productService.findById(id);
        view.addObject("product",product);
        view.setViewName("orders-add");
        return view;
    }

    //选择会员
    @RequestMapping("/selectMember.do")
    public ModelAndView selectMember(
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "8") Integer size) throws Exception {

        ModelAndView view = new ModelAndView();
        List<Member> memberList = memberService.findAll(page, size);

        PageInfo pageInfo = new PageInfo(memberList);
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }
        view.addObject("pageInfo",pageInfo);

        view.addObject("memberList",memberList);
        view.setViewName("select-member");
        return view;
    }

    //根据会员id查询会员信息给新建订单界面
    @RequestMapping("/getMemberById.do")
    public ModelAndView getMemberById(@RequestParam(name = "mid",required = true) Integer id) throws Exception {
        System.out.println("MEMBER:"+id);
        ModelAndView view = new ModelAndView();
        Member member = memberService.findById(id);
        view.addObject("member",member);
        view.setViewName("orders-add");
        return view;
    }

    //新建订单 保存操作
    @RequestMapping("/save.do")
    public String save(Orders orders,
                       @RequestParam(name = "memberId",required = true) Integer id) throws Exception{
        System.out.println("新建操作！");
        //保存订单信息
        //把订单信息，会员id保存到订单表中
        ordersService.save(orders,id);

        System.out.println(id+" Member");

        return "redirect:findAll.do";
    }

    //新建订单中新增旅客
    @RequestMapping("/saveTraveller.do")
    public String save(Traveller traveller) throws Exception {
        System.out.println(traveller.getCredentialsType()+" TravellerType");
        System.out.println(traveller.getName()+" TravellerName");

        travellerService.save(traveller);

        return "OK";
    }

    //查询所有没有跟订单关联的旅客
    @RequestMapping("/findByIdAddTraveller.do")
    public ModelAndView findByIdAddTraveller(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        ModelAndView view = new ModelAndView();
        //根据订单id查询订单
        Orders orders = ordersService.findByOrdersId(id);
        //查询所有没有跟订单关联的旅客
        List<Traveller> travellerList = travellerService.findOtherTraveller();
        view.addObject("orders",orders);
        view.addObject("travellerList",travellerList);
        view.setViewName("orders-traveller-add");
        return view;
    }

    //进行旅客关联 添加旅客
    @RequestMapping("/addTravellerToOrders.do")
    public String addTravellerToOrders(@RequestParam(name = "ordersId",required = true) Integer ordersId,
                                       @RequestParam(name = "ids",required = true) Integer[] travellerIds) throws Exception {

        ordersService.addTravellerToOrders(ordersId,travellerIds);

        return "redirect:findAll.do";
    }

    //根据订单id跳转产品选择界面
    @RequestMapping("/findByIdselectProduct.do")
    public ModelAndView findByIdselectProduct(
            @RequestParam(name = "ordersId",required = true) Integer ordersId,
            @RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
            @RequestParam(name = "size",required = true,defaultValue = "6") Integer size
    ) throws Exception {

        ModelAndView view = new ModelAndView();
        //根据订单id查询订单
        Orders orders = ordersService.findByOrdersId(ordersId);
        //查询所有产品
        List<Product> productList = productService.findAll();

        view.addObject("orders",orders);
        view.addObject("productList",productList);
        view.setViewName("select-product");
        return view;
    }

    //关联产品
    @RequestMapping("/addProductToProduct.do")
    public String addProductToProduct(@RequestParam(name = "ordersId",required = true) Integer ordersId,
                                      @RequestParam(name = "ids",required = true) Integer[] productId) throws Exception {

        ordersService.addProductToProduct(ordersId,productId);

        return "redirect:findAll.do";
    }

}
