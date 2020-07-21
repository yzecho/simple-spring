package io.yzecho.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 10:00
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue value) {
        this.propertyValueList.add(value);
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }
}
