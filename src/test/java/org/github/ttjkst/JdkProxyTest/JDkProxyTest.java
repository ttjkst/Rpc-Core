package org.github.ttjkst.JdkProxyTest;

import org.github.ttjkst.server.connector.proxy.JDK.ProxyJdkFactory;
import org.github.ttjkst.server.connector.proxy.JDK.RpcInvocationHandler;
import org.github.ttjkst.server.connector.proxy.ProxyFactory;
import org.github.ttjkst.service.IHello;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by ttjkst on 2017/9/6.
 */
public class JDkProxyTest {
    @Test
    public  void  proxyTest(){
        InvocationHandler handler = new RpcInvocationHandler();
        ProxyFactory factory = ProxyJdkFactory.setHandler(handler).build();
        IHello proxy = factory.createProxy(IHello.class);
        proxy.say();
        proxy.say("2");
        Constructor<?>[] constructors = proxy.getClass().getConstructors();
        System.out.println(constructors.toString());
        Class<?> proxyClass = Proxy.getProxyClass(Thread.currentThread().getContextClassLoader(), IHello.class);
        Constructor<?>[] constructors1 = proxyClass.getConstructors();
        System.out.println(constructors1);;

    }
}
