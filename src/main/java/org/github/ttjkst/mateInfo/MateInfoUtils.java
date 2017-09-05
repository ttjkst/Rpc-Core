package org.github.ttjkst.mateInfo;

import org.github.ttjkst.server.customer.process.annotation.ServerConnector;
import org.github.ttjkst.server.provider.annotation.ServerProvider;
import org.springframework.util.Assert;

/**
 * Created by ttjkst on 2017/9/5.
 */
public abstract class MateInfoUtils {
    public  static boolean  isConnector(Object o){
        ServerConnector annotation = o.getClass().getAnnotation(ServerConnector.class);
        return annotation!=null;
    }
    public static boolean isProvider(Object o){
        ServerProvider annotation = o.getClass().getAnnotation(ServerProvider.class);
        return annotation!=null;
    }

    public static ConnectorMateInfo parseConnector(Object o){
        if(isConnector(o)){
            ServerConnector annotation = o.getClass().getAnnotation(ServerConnector.class);
            return new ConnectorMateInfo(annotation.name(),annotation.version(),o.getClass(),annotation.timeout());
        }else{
            return  null;
        }
    }

    public static ProviderMateInfo parseProvider(Object o){
        if(isConnector(o)){
            ServerProvider annotation = o.getClass().getAnnotation(ServerProvider.class);
            return new ProviderMateInfo(annotation.name(),annotation.version(),o.getClass());
        }else{
            return  null;
        }
    }
}
