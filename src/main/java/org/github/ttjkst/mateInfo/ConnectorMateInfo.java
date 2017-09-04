package org.github.ttjkst.mateInfo;

/**
 * Created by ttjkst on 2017/9/4.
 */
public class ConnectorMateInfo {
    private String name;
    private String version;
    private Class  clazz;
    private int    timeout = 1000;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
