package io.yzecho.simple.aop;

/**
 * @author yzecho
 * @desc
 * @date 12/07/2020 22:57
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
