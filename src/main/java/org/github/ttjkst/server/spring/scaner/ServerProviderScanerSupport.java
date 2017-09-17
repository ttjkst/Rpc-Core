package org.github.ttjkst.server.spring.scaner;

import org.github.ttjkst.server.spring.annotation.EnableServerProvider;
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
public class ServerProviderScanerSupport implements ImportBeanDefinitionRegistrar {

    private static final String BEAN_NAME = "serverProviderScanerSupport";
    @Override
    public void registerBeanDefinitions( AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry ) {
        Set<String> packagesToScan = getPackagesToScan(importingClassMetadata);
        if (registry.containsBeanDefinition(BEAN_NAME)) {
            updatePostProcessor(registry, packagesToScan);
        }
        else {
            addPostProcessor(registry, packagesToScan);
        }
    }

    private Set<String> getPackagesToScan(AnnotationMetadata metadata){
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(EnableServerProvider.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        Set<String> packagesToScan = new LinkedHashSet<String>();
        packagesToScan.addAll(Arrays.asList(basePackages));
        for (Class<?> basePackageClass : basePackageClasses) {
            packagesToScan.add(ClassUtils.getPackageName(basePackageClass));
        }
        if (packagesToScan.isEmpty()) {
            return Collections
                    .singleton(ClassUtils.getPackageName(metadata.getClassName()));
        }
        return packagesToScan;
    }
    private void updatePostProcessor(BeanDefinitionRegistry registry,Set<String> packagesToScan){
        BeanDefinition definition = registry.getBeanDefinition(BEAN_NAME);
        ConstructorArgumentValues.ValueHolder constructorArguments = definition.getConstructorArgumentValues()
                .getGenericArgumentValue(Set.class);
        @SuppressWarnings("unchecked")
        Set<String> mergedPackages = (Set<String>) constructorArguments.getValue();
        mergedPackages.addAll(packagesToScan);
        constructorArguments.setValue(mergedPackages);
    }
    private void addPostProcessor(BeanDefinitionRegistry registry,Set<String> packagesToScan){
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ServerProviderPostProcessor.class);
        beanDefinition.getConstructorArgumentValues()
                .addGenericArgumentValue(packagesToScan);
        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(BEAN_NAME, beanDefinition);

    }
}
