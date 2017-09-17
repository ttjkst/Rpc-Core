package org.github.ttjkst.mateInfo;

import org.github.ttjkst.server.connector.annotation.ServerConnector;
import org.github.ttjkst.server.provider.annotation.ServerProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by ttjkst on 2017/9/5.
 */
public abstract class MateInfoUtils {
    public  static boolean  isConnector(Field o){
        ServerConnector annotation = o.getAnnotation(ServerConnector.class);
        return annotation!=null;
    }
    public static boolean isProvider(Object o){
        ServerProvider annotation = o.getClass().getAnnotation(ServerProvider.class);
        return annotation!=null;
    }
    public static boolean isProvider(Class<?> clazz){
        ServerProvider annotation = clazz.getAnnotation(ServerProvider.class);
        return annotation!=null;
    }

    public static ConnectorMateInfo parseConnector(Field o){
        if(isConnector(o)){
            ServerConnector annotation = o.getAnnotation(ServerConnector.class);
            return new ConnectorMateInfo(annotation.name(),annotation.version(),o.getClass(),annotation.timeout());
        }else{
            return  null;
        }
    }

    public static ProviderMateInfo parseProvider(Object o){
        if(isProvider(o)){
            ServerProvider annotation = o.getClass().getAnnotation(ServerProvider.class);
            return new ProviderMateInfo(annotation.name(),annotation.version(),o.getClass());
        }else{
            return  null;
        }
    }
    public static ProviderMateInfo parseProvider(Class<?> clazz){
        if(isProvider(clazz)){
            ServerProvider annotation = clazz.getAnnotation(ServerProvider.class);
            return new ProviderMateInfo(annotation.name(),annotation.version(),clazz);
        }else{
            return  null;
        }
    }
}
