package org.github.ttjkst.mateInfo;

/**
 * Created by ttjkst on 2017/9/4.
 */
public class ConnectorMateInfo {
    private String name;
    private String version;
    private Class  clazz;
    private int    timeout = 1000;
    private Object proxyBean;

    public Object getProxyBean() {
        return proxyBean;
    }

    public void setProxyBean(Object proxyBean) {
        this.proxyBean = proxyBean;
    }

    public ConnectorMateInfo(String name, String version, Class clazz, int timeout) {
        this.name = name;
        this.version = version;
        this.clazz = clazz;
        this.timeout = timeout;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectorMateInfo that = (ConnectorMateInfo) o;

        if (timeout != that.timeout) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        return clazz != null ? clazz.equals(that.clazz) : that.clazz == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + timeout;
        return result;
    }
}
