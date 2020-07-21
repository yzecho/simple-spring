package io.yzecho.aop;

import io.yzecho.ioc.BeanPostProcessor;
import io.yzecho.ioc.factory.BeanFactory;
import io.yzecho.ioc.factory.BeanFactoryAware;
import io.yzecho.ioc.xml.XmlBeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * @author yzecho
 * @desc
 * @date 20/07/2020 23:38
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private XmlBeanFactory xmlBeanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return true;
        }
        if (bean instanceof MethodInterceptor) {
            return true;
        }
        // 1、从 BeanFactory 查找 AspectJExpressionPointcutAdvisor 对象
        List<AspectJExpressionPointcutAdvisor> advisors =
                xmlBeanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            // 2、使用 Pointcut 对象匹配当前 bean 对象
            try {
                if (advisor.getPointcut().getClassFilter().matchers(bean.getClass())) {
                    ProxyFactory advisedSupport = new ProxyFactory();
                    advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                    advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMarcher());

                    TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                    advisedSupport.setTargetSource(targetSource);

                    // 3、生成代理对象并返回
                    return advisedSupport.getProxy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 2、匹配失败则返回bean
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        xmlBeanFactory = (XmlBeanFactory) beanFactory;
    }
}
