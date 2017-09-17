package org.github.ttjkst.server.connector.proxy.JDK;


import com.google.common.base.Strings;
import org.github.ttjkst.packages.MessagePackage;
import org.github.ttjkst.server.connector.annotation.ServerConnector;
import org.github.ttjkst.server.connector.process.MethodExcutorProcess;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class RpcInvocationHandler implements InvocationHandler {

    private MethodExcutorProcess process;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String   methodName         = method.getName();
        Class<?> declaringClass     = method.getDeclaringClass();
        args = Objects.isNull(args)?new Object[0]:args;
        Class[]  classes            = Arrays.asList(args).stream().map(Object::getClass).toArray(Class[]::new);
        ServerConnector serverConnectorInfo = declaringClass.getAnnotation(ServerConnector.class);
        String className = Strings.isNullOrEmpty(serverConnectorInfo.name())?declaringClass.getSimpleName():serverConnectorInfo.name();
        MessagePackage msgPack = new MessagePackage();
        msgPack.setTypes(classes);
        msgPack.setValues(args);
        System.out.println(msgPack);
        return process.excute(msgPack,className,methodName,serverConnectorInfo.version(),serverConnectorInfo.timeout());
    }

    public RpcInvocationHandler setProcess(MethodExcutorProcess process) {
        this.process = process;
        return this;
    }
}
