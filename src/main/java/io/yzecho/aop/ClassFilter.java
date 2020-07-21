package io.yzecho.aop;

/**
 * @author yzecho
 * @desc
 * @date 20/07/2020 23:11
 */
public interface ClassFilter {
    Boolean matchers(Class<?> beanClass) throws Exception;
}
