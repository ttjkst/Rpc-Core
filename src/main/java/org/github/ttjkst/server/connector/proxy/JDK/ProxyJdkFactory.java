package org.github.ttjkst.server.connector.proxy.JDK;

import org.github.ttjkst.server.connector.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class ProxyJdkFactory implements ProxyFactory{

    private   static ProxyJdkFactory proxyJdkFactory = null;
    private   static InvocationHandler handler       = null;
    private  ProxyJdkFactory(){

    };

    public static ProxyJdkFactory setHandler(InvocationHandler handler) {
        ProxyJdkFactory.handler = handler;
        return proxyJdkFactory;
    }

    public static ProxyJdkFactory build() {
        if(proxyJdkFactory==null){
            proxyJdkFactory = new ProxyJdkFactory();
        }
        return proxyJdkFactory;
    }
    @Override
    public <T> T createProxy(Class<T> t){
        return createProxy(t,Thread.currentThread().getContextClassLoader());
    }
    public <T> T createProxy(Class<T> t,ClassLoader classLoader){
        return (T)Proxy.newProxyInstance(classLoader,new Class[]{t},new RpcInvocationHandler());
    };
}
