package io.yzecho.aop;

/**
 * @author yzecho
 * @desc
 * @date 20/07/2020 22:18
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
