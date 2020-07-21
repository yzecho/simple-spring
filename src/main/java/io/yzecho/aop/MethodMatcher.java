package io.yzecho.aop;

import java.lang.reflect.Method;

/**
 * @author yzecho
 * @desc
 * @date 20/07/2020 16:21
 */
public interface MethodMatcher {
    Boolean matchers(Method method, Class<?> beanClass);
}
