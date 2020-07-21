package io.yzecho.ioc;

import io.yzecho.bean.Student;
import io.yzecho.bean.Teacher;
import io.yzecho.ioc.xml.XmlBeanFactory;
import io.yzecho.simple.aop.HelloService;
import org.junit.Test;

/**
 * @author yzecho
 * @desc
 * @date 14/07/2020 07:55
 */
public class XmlBeanFactoryTest {

    @Test
    public void getBean() throws Exception {
        System.out.println("-----IOC TEST-----");
        String location = getClass().getClassLoader().getResource("spring-ioc.xml").getFile();
        XmlBeanFactory factory = new XmlBeanFactory(location);
        Teacher teacher = (Teacher) factory.getBean("teacher");
        System.out.println(teacher);
        Student student = (Student) factory.getBean("student");
        System.out.println(student);

        System.out.println("-----AOP TEST-----");
        HelloService helloService = (HelloService) factory.getBean("helloService");
        helloService.sayHello();
    }
}
