package org.github.ttjkst.config;

import org.github.ttjkst.protocol.ProtocolProcess;
import org.github.ttjkst.protocol.hesssian2.Hessian2ProtocolProtocalProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ttjkst on 2017/8/30.
 */
@Configuration
public class ProtocolProcessConfig {

    @Bean
    public ProtocolProcess getProcess(){
        return new Hessian2ProtocolProtocalProcess();
    }
}
