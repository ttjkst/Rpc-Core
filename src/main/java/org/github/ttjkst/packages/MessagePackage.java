package org.github.ttjkst.packages;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class MessagePackage implements Serializable{
    private Object[] values;
    private Class[]   types;

    public Object[] getValues() {
        return values;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "MessagePackage{" +
                "values=" + Arrays.toString(values) +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
