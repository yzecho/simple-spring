package io.yzecho.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author yzecho
 * @desc
 * @date 14/07/2020 07:40
 */
public class LogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(methodInvocation.getMethod().getName() + "method start");
        Object obj = methodInvocation.proceed();
        System.out.println(methodInvocation.getMethod().getName() + "method end");
        return obj;
    }
}
