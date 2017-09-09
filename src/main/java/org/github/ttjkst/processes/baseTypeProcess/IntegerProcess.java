package org.github.ttjkst.processes.baseTypeProcess;

import org.github.ttjkst.processes.TypeProcess;

/**
 * Created by ttjkst on 2017/8/28.
 */
public class IntegerProcess implements TypeProcess<Integer>{
    @Override
    public Integer get(String src) {
        return Integer.valueOf(src);
    }

    @Override
    public boolean support(Class<?> clazz) {
        return Integer.class.equals(clazz)||int.class.equals(clazz);
    }
}
