package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.tl.ssm.domain.Product;
import com.tl.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //查询出所有产品信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "6") Integer size
    ) throws Exception {
        ModelAndView view = new ModelAndView();
        List<Product> products = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(products);
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }
        view.addObject("pageInfo",pageInfo);
        view.setViewName("product-list");
        return view;
    }

    //添加产品信息
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
