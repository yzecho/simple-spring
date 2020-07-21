package io.yzecho.ioc;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 16:25
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
