package io.yzecho.ioc.xml;

import io.yzecho.aop.AspectJExpressionPointcutAdvisor;
import io.yzecho.ioc.BeanDefinition;
import io.yzecho.ioc.BeanPostProcessor;
import io.yzecho.ioc.BeanReference;
import io.yzecho.ioc.PropertyValue;
import io.yzecho.ioc.factory.BeanFactory;
import io.yzecho.ioc.factory.BeanFactoryAware;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 16:21
 */
public class XmlBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private List<String> beanDefinitionNames = new ArrayList<>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private XmlBeanDefinitionReader beanDefinitionReader;

    public XmlBeanFactory(String location) throws Exception {
        beanDefinitionReader = new XmlBeanDefinitionReader();
        loadBeanDefinitions(location);
    }

    private void loadBeanDefinitions(String location) throws Exception {
        beanDefinitionReader.loadBeanDefinitions(location);
        registerBeanDefinition();
        registerBeanPostProcessor();
    }

    private void registerBeanPostProcessor() {
        List<AspectJExpressionPointcutAdvisor> beans = getBeansForType(BeanPostProcessor.class);
        beans.forEach(bean -> addBeanPostProcessor((BeanPostProcessor) bean));
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class<?> type) {
        List beans = new ArrayList<>();
        beanDefinitionNames.forEach(beanDefinitionName -> {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                try {
                    beans.add(getBean(beanDefinitionName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return beans;
    }

    private void registerBeanDefinition() {
        beanDefinitionReader.getRegistry().forEach((name, beanDefinition) -> {
            beanDefinitionMap.put(name, beanDefinition);
            beanDefinitionNames.add(name);
        });
    }

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("no this bean with name " + name);
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = createBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }

        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition bd) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : bd.getPropertyValues().getPropertyValueList()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }


}
