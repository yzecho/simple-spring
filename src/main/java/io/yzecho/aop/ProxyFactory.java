package io.yzecho.aop;

/**
 * @author yzecho
 * @desc
 * @date 16/07/2020 22:47
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    private AopProxy createAopProxy() {
        return new JdkDynamicAopProxy(this);
    }

    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
}
