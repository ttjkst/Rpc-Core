package org.github.ttjkst.mateInfo;

/**
 * Created by ttjkst on 2017/9/4.
 */
public class ProviderMateInfo {
    private String name;
    private String version;
    private Class  clazz;


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

    public ProviderMateInfo(String name, String version, Class clazz) {
        this.name = name;
        this.version = version;
        this.clazz = clazz;
    }
}
