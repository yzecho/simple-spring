package io.yzecho.aop;

import io.yzecho.simple.aop.HelloService;
import io.yzecho.simple.aop.HelloServiceImpl;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author yzecho
 * @desc
 * @date 21/07/2020 22:24
 */
public class AopTest {

    @Test
    public void jdkDynamicAopProxyTest() {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new LogInterceptor());
        HelloService helloService = new HelloServiceImpl();

        TargetSource targetSource = new TargetSource(helloService, HelloServiceImpl.class, HelloServiceImpl.class.getInterfaces());
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher((method, beanClass) -> true);
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        helloService = (HelloService) jdkDynamicAopProxy.getProxy();
        helloService.sayHello();
    }
}
