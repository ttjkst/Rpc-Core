package org.github.ttjkst.beanFactory;

import org.github.ttjkst.server.customer.process.MethodExcutorProcess;
import org.github.ttjkst.server.customer.proxy.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by ttjkst on 2017/9/5.
 */
public class RpcBeanFactoryPostProcessor implements BeanPostProcessor {
   private ProxyFactory factory;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return factory.createProxy(bean.getClass());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public void setFactory(ProxyFactory factory) {
        this.factory = factory;
    }
}
