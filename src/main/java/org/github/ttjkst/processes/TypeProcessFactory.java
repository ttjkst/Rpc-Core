package org.github.ttjkst.processes;

import org.github.ttjkst.processes.baseTypeProcess.IntegerProcess;
import org.github.ttjkst.processes.baseTypeProcess.StringProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ttjkst on 2017/8/28.
 */
public  class TypeProcessFactory {
    private  List<TypeProcess> list = new ArrayList<>();
    private static TypeProcessFactory factory = null;
    private TypeProcessFactory() {
        IntegerProcess intergeProcess = new IntegerProcess();
        StringProcess stringProcess   = new StringProcess();
        list.add(intergeProcess);
        list.add(stringProcess);
    }
    public static TypeProcessFactory build(){
        factory= factory == null?new TypeProcessFactory():factory;
        return factory;
    }
    
    public TypeProcess getProcess(Class<?> clazz){
        List<TypeProcess> collect = list.stream().filter(x -> x.support(clazz)).collect(Collectors.toList());
        if(collect.size()!=1){
            throw  new  RuntimeException("can not find a process or find two process in TypeProcessFactory");
        }
        return collect.get(0);
    }
}
