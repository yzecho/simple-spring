package io.yzecho.aop;

import org.aopalliance.aop.Advice;

/**
 * @author yzecho
 * @desc
 * @date 21/07/2020 10:34
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }

    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

}
