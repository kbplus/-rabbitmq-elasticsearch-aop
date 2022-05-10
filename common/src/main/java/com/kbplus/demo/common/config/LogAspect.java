package com.kbplus.demo.common.config;

import com.kbplus.demo.common.annotation.SystemLog;
import com.kbplus.demo.common.entity.MyLog;
import com.kbplus.demo.common.sender.ModuleRequestSender;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Order(1)
@Component
@Aspect
public class LogAspect {

    @Autowired
    private ModuleRequestSender moduleRequestSender;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.kbplus.demo.common.annotation.SystemLog)")
    public void doAspect() {
    }

    @Around("doAspect()")
    public Object doAround(ProceedingJoinPoint pjd) throws Throwable {

        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes==null){
            return null;
        }
        HttpServletRequest request = attributes.getRequest();

        //获取方法注释
        String operation="";
        String logType="";
        Signature signature=pjd.getSignature();
        if (signature instanceof MethodSignature) {
            Class<?> targetCls=pjd.getTarget().getClass();
            MethodSignature ms= (MethodSignature)signature;
            Method targetMethod=targetCls.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
            SystemLog requiredLog=targetMethod.getAnnotation(SystemLog.class);
            if(requiredLog!=null) {
                operation = requiredLog.description();
                logType = requiredLog.type().getName();
            }
        }

        List<String> params = new ArrayList<>();

        //获取参数
        Enumeration<String> enu = request.getParameterNames();
        if(enu.hasMoreElements()) {
            while (enu.hasMoreElements()) {
                String paraName = enu.nextElement();
                params.add(paraName + ": " + request.getParameter(paraName));
            }
        }else {
            Object[] args = pjd.getArgs();
            for (Object arg : args) {
                params.add(arg.toString());
            }
        }


        MyLog myLog = new MyLog();
        myLog.setId(UUID.randomUUID().toString());
        myLog.setMethodType(request.getMethod());
        myLog.setType(logType);
        myLog.setParams(params);
        myLog.setModuleName(request.getContextPath().substring(1));
        myLog.setUrl(request.getRequestURI());
        myLog.setIp(request.getRemoteAddr());
        myLog.setComment(operation);
        long startTime = System.currentTimeMillis();
        Object proceed = pjd.proceed();
        myLog.setCost(System.currentTimeMillis()-startTime);

        moduleRequestSender.send(myLog);

        return proceed;
    }
}
