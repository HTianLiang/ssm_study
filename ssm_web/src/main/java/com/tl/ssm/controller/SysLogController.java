package com.tl.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.tl.ssm.domain.SysLog;
import com.tl.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "6") Integer size
    ) throws Exception {
        ModelAndView view = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll(page,size);
        //PageInfo 一个分页Bean
        PageInfo pageInfo = new PageInfo(sysLogs);

        //总共有多少页数据  总数除去每页多少条数据
        int pageCount = (int) (pageInfo.getTotal()/pageInfo.getPageSize());
        //总数除去每页条数取余 余数的都+1，整除的则不用作加减
        if (pageInfo.getTotal()%pageInfo.getPageSize() != 0){
            view.addObject("pageCount",pageCount+1);
        }else {
            view.addObject("pageCount",pageCount);
        }

        view.addObject("pageInfo",pageInfo);
        view.setViewName("syslog-list");
        return view;
    }

}
