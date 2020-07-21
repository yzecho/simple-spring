package io.yzecho.simple.ioc;

import io.yzecho.bean.Student;
import io.yzecho.bean.Teacher;
import org.junit.Test;

/**
 * @author yzecho
 * @desc
 * @date 12/07/2020 19:36
 */
public class SimpleInversionOfControlTest {
    @Test
    public void getBean() {
        String location = SimpleInversionOfControl.class.getClassLoader().getResource("simple-ioc.xml").getFile();
        SimpleInversionOfControl simpleInversionOfControl = null;
        try {
            simpleInversionOfControl = new SimpleInversionOfControl(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert simpleInversionOfControl != null;
        Teacher teacher = (Teacher) simpleInversionOfControl.getBean("teacher");
        System.out.println(teacher);
        Student student = (Student) simpleInversionOfControl.getBean("student");
        System.out.println(student);
    }
}
