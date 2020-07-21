package io.yzecho.simple.aop;

import java.lang.reflect.Proxy;

/**
 * @author yzecho
 * @desc
 * @date 12/07/2020 22:47
 */
public class SimpleAspectOrientedProgramming {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAspectOrientedProgramming.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }
}
