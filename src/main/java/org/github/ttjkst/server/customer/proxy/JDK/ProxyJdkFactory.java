package org.github.ttjkst.server.customer.proxy.JDK;

import org.github.ttjkst.server.customer.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class ProxyJdkFactory implements ProxyFactory{

    public static ProxyJdkFactory proxyJdkFactory = null;
    public  static InvocationHandler handler;
    private  ProxyJdkFactory(){

    };


    public ProxyJdkFactory build() {
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
