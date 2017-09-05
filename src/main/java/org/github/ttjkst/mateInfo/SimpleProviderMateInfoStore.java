package org.github.ttjkst.mateInfo;

import com.google.common.base.Strings;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ttjkst on 2017/9/4.
 */
public class SimpleProviderMateInfoStore implements PrvoiderMateInfoStore{
    private Map<String,ConnectorMateInfo> map = new ConcurrentHashMap<>();

    private static PrvoiderMateInfoStore prvoiderMateInfoStore = new SimpleProviderMateInfoStore();

    @Override
    public boolean store(ConnectorMateInfo mateInfo) {
        if(Objects.isNull(mateInfo)||hasMate(mateInfo)){
            return false;
        }
        map.put(getName(mateInfo),mateInfo);
        return true;
    }

    private String getName(ConnectorMateInfo info){
        return Strings.isNullOrEmpty(info.getName())?info.getClazz().getName():info.getName();
    }
    @Override
    public boolean hasMate(ConnectorMateInfo mateInfo) {

        return map.containsKey(getName(mateInfo));
    }

    @Override
    public void delete(ConnectorMateInfo mateInfo) {
            map.remove(getName(mateInfo));
    }

    @Override
    public void changeMateInfoByName(ConnectorMateInfo mateInfo) {
            map.put(getName(mateInfo),mateInfo);
    }

    @Override
    public ConnectorMateInfo findByClass(Class clazz) {
        Optional<Map.Entry<String, ConnectorMateInfo>> first = map.entrySet().stream().filter(x -> x.getValue().getClazz().equals(clazz)).findFirst();
        if(first.isPresent()){
            return first.get().getValue();
        }
        return null;
    }

    @Override
    public PrvoiderMateInfoStore build() {
        return prvoiderMateInfoStore;
    }
}
