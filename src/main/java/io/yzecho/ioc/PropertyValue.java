package io.yzecho.ioc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yzecho
 * @desc
 * @date 13/07/2020 10:00
 */
@Data
@AllArgsConstructor
public class PropertyValue {
    private final String name;
    private final Object value;
}
