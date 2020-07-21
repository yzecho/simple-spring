package io.yzecho.ioc;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 16:56
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
