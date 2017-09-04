package org.github.ttjkst.processes;

/**
 * Created by ttjkst on 2017/8/28.
 */
public interface TypeProcess<T> {
     T get(String src);
     boolean support(Class<?> clazz);
}
