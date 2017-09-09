package org.github.ttjkst.server.spring.postProcessor;

import org.github.ttjkst.server.provider.annotation.ServerProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ttjkst on 2017/9/9.
 */
public class ServerProviderPostProcessor implements BeanFactoryPostProcessor,ApplicationContextAware {

    private final Set<String> packagesToScan;

    private ApplicationContext applicationContext;

    public ServerProviderPostProcessor( Set<String> packagesToScan ) {
        this.packagesToScan = packagesToScan;
    }

    @Override
    public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException {
        ClassPathScanningCandidateComponentProvider componentProvider = createComponentProvider();
        try {
            for(String packageToScan:this.packagesToScan){
                scanPackage(componentProvider,packageToScan);
            }
        } catch (Exception e) {
            throw  new BeanCreationException(e.getMessage());
        }
    }

    private void scanPackage(
            ClassPathScanningCandidateComponentProvider componentProvider,
            String packageToScan) throws ClassNotFoundException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry)applicationContext;
        for (BeanDefinition beanDefinition : componentProvider.findCandidateComponents(packageToScan)) {
            if (beanDefinition instanceof ScannedGenericBeanDefinition) {
                ScannedGenericBeanDefinition candidate = (ScannedGenericBeanDefinition) beanDefinition;
                Map<String, Object> attributes = candidate.getMetadata().
                        getAnnotationAttributes(ServerProvider.class.getName());
                String className = candidate.getBeanClassName();
                Class beanClass = Class.forName(className);
                BeanDefinitionBuilder builder = BeanDefinitionBuilder
                        .rootBeanDefinition(beanClass);
                String name = determineName(attributes, candidate);
                registry.registerBeanDefinition(name, builder.getBeanDefinition());
            }
        }
    }

    private String determineName(Map<String, Object> attributes,
                                 BeanDefinition beanDefinition) {
        return (String) (StringUtils.hasText((String) attributes.get("name"))
                ? attributes.get("name") : getName(beanDefinition.getBeanClassName()));
    }

    //做store
    private String getName(String className){
        String name = UUID.randomUUID().toString();
        try {
            name = Class.forName(className).getSimpleName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }



    private ClassPathScanningCandidateComponentProvider createComponentProvider() {
        ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(
                false);
        componentProvider.setEnvironment(this.applicationContext.getEnvironment());
        componentProvider.setResourceLoader(this.applicationContext);
        componentProvider.addIncludeFilter(getServerProviderTypeFilter());
        return componentProvider;
    }

    private TypeFilter getServerProviderTypeFilter(){

        return new AnnotationTypeFilter(ServerProvider.class);
    }
    Set<String> getPackagesToScan() {
        return Collections.unmodifiableSet(this.packagesToScan);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}
