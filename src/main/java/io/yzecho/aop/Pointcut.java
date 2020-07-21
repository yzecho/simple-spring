package io.yzecho.aop;

/**
 * @author yzecho
 * @desc
 * @date 20/07/2020 19:00
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMarcher();
}
