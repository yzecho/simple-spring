package io.yzecho.simple.aop;

import org.junit.Test;

/**
 * @author yzecho
 * @desc
 * @date 12/07/2020 23:03
 */
public class SimpleAspectOrientedProgrammingTest {
    @Test
    public void getProxy() {
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloService = new HelloServiceImpl();

        // 2. 创建一个 Advice
        BeforeAdvice beforeAdvice = new BeforeAdvice(helloService, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceProxy = (HelloService) SimpleAspectOrientedProgramming.getProxy(helloService, beforeAdvice);
        helloServiceProxy.sayHello();
    }
}
