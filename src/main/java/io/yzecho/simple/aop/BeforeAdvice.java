package io.yzecho.simple.aop;

import java.lang.reflect.Method;

/**
 * @author yzecho
 * @desc
 * @date 12/07/2020 22:37
 */
public class BeforeAdvice implements Advice {
    private final Object bean;
    private final MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法执行前调用通知
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
