package org.github.ttjkst.server.customer.proxy.JDK;

import java.lang.reflect.Proxy;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class ProxyJdkFactory {

    public ProxyJdkFactory proxyJdkFactory = null;
    private  ProxyJdkFactory(){

    };

    public ProxyJdkFactory build() {
        if(proxyJdkFactory==null){
            proxyJdkFactory = new ProxyJdkFactory();
        }
        return proxyJdkFactory;
    }
    public <T> T createProxy(Class<T> t){
        return createProxy(t,Thread.currentThread().getContextClassLoader());
    }
    public <T> T createProxy(Class<T> t,ClassLoader classLoader){
        return (T)Proxy.newProxyInstance(classLoader,new Class[]{t},new RpcInvocationHandler());
    };
}
