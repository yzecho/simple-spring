package io.yzecho.ioc;

import lombok.Data;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 16:45
 */
@Data
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }
}
