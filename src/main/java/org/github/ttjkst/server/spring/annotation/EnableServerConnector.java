package org.github.ttjkst.server.spring.annotation;

import org.github.ttjkst.server.spring.scaner.ServerConnectorScanerSupport;
import org.github.ttjkst.server.spring.scaner.ServerProviderScanerSupport;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ttjkst on 2017/9/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ServerConnectorScanerSupport.class)
public @interface EnableServerConnector {

}
