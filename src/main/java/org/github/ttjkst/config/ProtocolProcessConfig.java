package org.github.ttjkst.config;

import org.github.ttjkst.protocol.ProtocolProcess;
import org.github.ttjkst.protocol.hesssian2.Hessian2ProtocolProtocalProcess;
import org.github.ttjkst.server.connector.process.Hessian2.Hessian2RestExcutorProcess;
import org.github.ttjkst.server.connector.process.MethodExcutorProcess;
import org.github.ttjkst.server.connector.proxy.JDK.ProxyJdkFactory;
import org.github.ttjkst.server.connector.proxy.JDK.RpcInvocationHandler;
import org.github.ttjkst.server.connector.proxy.ProxyFactory;
import org.github.ttjkst.server.spring.config.ConnectorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ttjkst on 2017/8/30.
 */
@Configuration
public class ProtocolProcessConfig {

    @Autowired
    private ConnectorProperties configProperties;

    @Bean
    public ProtocolProcess getProcess(){
        return new Hessian2ProtocolProtocalProcess();
    }


    @Bean
    @ConditionalOnProperty(prefix = "rpc.connector")
    public ProxyFactory getFactory(){
        MethodExcutorProcess process = new Hessian2RestExcutorProcess().
                setServerUrl(configProperties.getUrl());
        RpcInvocationHandler handler = new RpcInvocationHandler().setProcess(process);
      return ProxyJdkFactory.setHandler(handler).build();
    }
}
