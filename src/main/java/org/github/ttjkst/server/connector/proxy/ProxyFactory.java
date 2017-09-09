package org.github.ttjkst.server.connector.proxy;

/**
 * Created by ttjkst on 2017/9/5.
 */
public interface ProxyFactory {
    <T> T createProxy(Class<T> t);

}
