package io.yzecho.ioc;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 16:22
 */
@Data
@NoArgsConstructor
public class BeanDefinition {
    private Object bean;
    private Class<?> beanClass;
    private String beanClassName;
    private PropertyValues propertyValues = new PropertyValues();

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
