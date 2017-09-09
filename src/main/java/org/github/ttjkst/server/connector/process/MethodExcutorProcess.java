package org.github.ttjkst.server.connector.process;

import org.github.ttjkst.packages.MessagePackage;

/**
 * Created by ttjkst on 2017/9/1.
 */
public interface MethodExcutorProcess {
    Object excute(MessagePackage msgPackage,String className,String methodName,String version,int timeout);
}
