package org.github.ttjkst.server.spring.scaner;

import org.github.ttjkst.server.spring.annotation.EnableServerProvider;
import org.github.ttjkst.server.spring.postProcessor.ConnectorAnnotationBeanPostProcess;
import org.github.ttjkst.server.spring.postProcessor.ServerProviderPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by ttjkst on 2017/9/8.
 */
public class ServerConnectorScanerSupport implements ImportBeanDefinitionRegistrar {

    private static final String BEAN_NAME = "serverConnectorScanerSupport";
    @Override
    public void registerBeanDefinitions( AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry ) {
        if (registry.containsBeanDefinition(BEAN_NAME)) {
            updatePostProcessor(registry);
        }
        else {
            addPostProcessor(registry);
        }
    }

    //do nothing but affter it will marge some Config properties
    private void updatePostProcessor(BeanDefinitionRegistry registry){
        BeanDefinition definition = registry.getBeanDefinition(BEAN_NAME);
    }
    private void addPostProcessor(BeanDefinitionRegistry registry){
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ConnectorAnnotationBeanPostProcess.class);
        registry.registerBeanDefinition(BEAN_NAME, beanDefinition);

    }
}
