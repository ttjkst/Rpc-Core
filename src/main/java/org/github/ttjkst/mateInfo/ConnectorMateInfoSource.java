package org.github.ttjkst.mateInfo;

/**
 * Created by ttjkst on 2017/9/4.
 */
public interface ConnectorMateInfoSource {
    boolean store(ConnectorMateInfo mateInfo);
    boolean hasMate(ConnectorMateInfo mateInfo);
    void delete(ConnectorMateInfo mateInfo);
    void changeMateInfoByName(ConnectorMateInfo mateInfo);
    ConnectorMateInfo findByMateInfo(ConnectorMateInfo mateInfo);
    ConnectorMateInfoSource build();
}
