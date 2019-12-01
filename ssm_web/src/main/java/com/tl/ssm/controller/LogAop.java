package com.tl.ssm.controller;

import com.tl.ssm.domain.SysLog;
import com.tl.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method; //访问的方法

    //前置通知  主要是获取开始时间，执行的类是哪个，执行的方法是哪个
    @Before("execution(* com.tl.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        System.out.println("前置通知");
        visitTime = new Date(); //开始时间就是当前时间
        clazz = jp.getTarget().getClass(); //访问的类
        //获取访问的方法
        String methodName = jp.getSignature().getName(); //访问方法的名称
        System.out.println("MethodName:"+methodName);
        Object[] jpArgs = jp.getArgs(); //访问方法的参数
        System.out.println("AGR:"+jpArgs);

        //获取具体执行的方法的Method对象
        if (jpArgs == null || jpArgs.length ==0){
            method = clazz.getMethod(methodName);
            System.out.println("No,M:"+method);//只能获取无参数的方法
        }else {
            //有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classes = new Class[jpArgs.length];
            for (int i = 0;i < jpArgs.length; i++){
                classes[i] = jpArgs[i].getClass();
            }
            method = clazz.getMethod(methodName,classes); //获取有参数的方法
        }
    }

    //后置通知
    @After("execution(* com.tl.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long executionTime = new Date().getTime()-visitTime.getTime();//获取访问时长
        System.out.println("后置通知"+executionTime);
        System.out.println("M:"+method);
        //获取url 通过反射操作
        String url = "";
        if (clazz != null && method!=null && clazz != LogAop.class && clazz != SysLogController.class){
            //获取类上的@RequestMapping("xxx")
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] clazzValue = clazzAnnotation.value();
                //获取方法上的@RequestMapping("xxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();

                    url = clazzValue[0]+methodValue[0];

                    //获取ip地址
                    String ip = request.getRemoteAddr();

                    //获取当前用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将其日志信息封装到SysLog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUsername(username);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setMethod("[类名] "+clazz.getName()+" [方法名] "+method.getName());

                    System.out.println("Method:"+method);
                    //调用service层将sysLog保存到数据库
                    sysLogService.save(sysLog);

                }
            }
        }
    }

}