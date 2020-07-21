package io.yzecho.aop;

/**
 * @author yzecho
 * @desc
 * @date 16/07/2020 22:39
 */
public abstract class AbstractAopProxy implements AopProxy {
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

}
