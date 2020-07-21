package io.yzecho.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author yzecho
 * @desc
 * @date 12/07/2020 18:00
 */
@Data
@ToString
public class Teacher {
    private String teacherName;
    private String teacherAddress;
    private Student student;
}
