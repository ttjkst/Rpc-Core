package org.github.ttjkst.server.customer.process.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ttjkst on 2017/9/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServerConnector {
    String name();
    String version() default "0.0.1";
    int timeout() default 1000;

}
