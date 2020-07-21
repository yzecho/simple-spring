package io.yzecho.aop;

import io.yzecho.simple.aop.HelloService;
import io.yzecho.simple.aop.HelloServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author yzecho
 * @desc
 * @date 21/07/2020 23:13
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() throws Exception {
        String expression = "execution(* io.yzecho.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        Boolean matchers = aspectJExpressionPointcut.matchers(HelloService.class);
        assertTrue(matchers);
    }

    @Test
    public void testMethodMatcher() throws NoSuchMethodException {
        String expression = "execution(* io.yzecho.*.sayHello(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        Boolean matchers = aspectJExpressionPointcut.matchers(HelloServiceImpl.class.getDeclaredMethod("sayHello"), HelloServiceImpl.class);
        assertTrue(matchers);
    }
}
