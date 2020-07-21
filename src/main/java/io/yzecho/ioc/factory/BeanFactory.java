package io.yzecho.ioc.factory;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 09:28
 */
public interface BeanFactory {
    Object getBean(String id) throws Exception;
}
