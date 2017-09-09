package org.github.ttjkst.mateInfo;

import com.google.common.base.Strings;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ttjkst on 2017/9/4.
 */
public class SimpleConnectorMateInfoStore implements ConnectorMateInfoSource{
    private Map<Integer,ConnectorMateInfo> map = new ConcurrentHashMap<>();

    private static ConnectorMateInfoSource source = new SimpleConnectorMateInfoStore();

    @Override
    public boolean store(ConnectorMateInfo mateInfo) {
        if(Objects.isNull(mateInfo)||hasMate(mateInfo)){
            return false;
        }
        map.put(mateInfo.hashCode(),mateInfo);
        return true;
    }
    @Override
    public boolean hasMate(ConnectorMateInfo mateInfo) {

        return map.containsKey(mateInfo.hashCode());
    }

    @Override
    public void delete(ConnectorMateInfo mateInfo) {
            map.remove(mateInfo.hashCode());
    }

    @Override
    public void changeMateInfoByName(ConnectorMateInfo mateInfo) {
            map.put(mateInfo.hashCode(),mateInfo);
    }


    @Override
    public ConnectorMateInfo findByMateInfo(ConnectorMateInfo mateInfo) {
        return map.get(mateInfo.hashCode());
    }

    @Override
    public ConnectorMateInfoSource build() {
        return source;
    }
}
