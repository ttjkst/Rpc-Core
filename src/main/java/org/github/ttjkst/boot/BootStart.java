package org.github.ttjkst.boot;

import org.github.ttjkst.server.spring.annotation.EnableServerProvider;
import org.github.ttjkst.server.spring.config.ConnectorProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ttjkst on 2017/8/24.
 */

@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties({ConnectorProperties.class})
@ComponentScan("org.github.ttjkst.*")
public class BootStart {
    public static void main(String... args){
        SpringApplication.run(BootStart.class,args);
    }
}
