package org.github.ttjkst.server.spring.postProcessor;

import org.github.ttjkst.mateInfo.ConnectorMateInfo;
import org.github.ttjkst.mateInfo.ConnectorMateInfoSource;
import org.github.ttjkst.mateInfo.MateInfoUtils;
import org.github.ttjkst.mateInfo.SimpleConnectorMateInfoStore;
import org.github.ttjkst.server.connector.proxy.JDK.ProxyJdkFactory;
import org.github.ttjkst.server.connector.proxy.JDK.RpcInvocationHandler;
import org.github.ttjkst.server.connector.proxy.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AliasFor;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by ttjkst on 2017/9/5.
 */
public class ConnectorAnnotationBeanPostProcess implements BeanPostProcessor{

    private ConnectorMateInfoSource mateInfoSource = new SimpleConnectorMateInfoStore().build();

    @Autowired
    private ProxyFactory proxyFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        declaredFields=declaredFields==null?new Field[0]:declaredFields;
        Arrays.asList(declaredFields).stream().filter(this::supprotAnnotationType).forEach(x->{
            Object proxy = null;
            ConnectorMateInfo connectorMateInfo = MateInfoUtils.parseConnector(x);
            if(mateInfoSource.hasMate(connectorMateInfo)){
                proxy = mateInfoSource.findByMateInfo(connectorMateInfo).getProxyBean();
            }else{
                proxy = proxyFactory.createProxy(x.getType());
                connectorMateInfo.setProxyBean(proxy);
                mateInfoSource.store(connectorMateInfo);
            }
            try {
                x.setAccessible(true);
                x.set(bean,proxy);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private boolean supprotAnnotationType(Field field){
        return MateInfoUtils.isConnector(field);
    }
}
