package org.github.ttjkst.server.customer.proxy.JDK;


import com.google.common.base.Strings;
import org.github.ttjkst.packages.MessagePackage;
import org.github.ttjkst.server.customer.process.annotation.ServerConnector;
import org.github.ttjkst.server.customer.process.MethodExcutorProcess;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class RpcInvocationHandler implements InvocationHandler {

    private MethodExcutorProcess process;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String   methodName         = method.getName();
        Class<?> declaringClass     = method.getDeclaringClass();
        Class[]  classes            = Arrays.asList(args).stream().map(Object::getClass).toArray(Class[]::new);
        ServerConnector providerInfo =  declaringClass.getAnnotation(ServerConnector.class);
        String className = Strings.isNullOrEmpty(providerInfo.name())?declaringClass.getSimpleName():providerInfo.name();
        MessagePackage msgPack = new MessagePackage();
        msgPack.setTypes(classes);
        msgPack.setValues(args);
        return process.excute(msgPack,className,methodName,providerInfo.version(),providerInfo.timeout());
    }

    public void setProcess(MethodExcutorProcess process) {
        this.process = process;
    }
}
