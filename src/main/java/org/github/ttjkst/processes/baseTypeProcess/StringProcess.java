package org.github.ttjkst.processes.baseTypeProcess;

import org.github.ttjkst.processes.TypeProcess;

/**
 * Created by ttjkst on 2017/8/28.
 */
public class StringProcess implements TypeProcess<String>{
    @Override
    public String get(String src) {
        return src;
    }

    @Override
    public boolean support(Class<?> clazz) {
        return String.class.equals(clazz);
    }

}
