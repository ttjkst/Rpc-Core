package org.github.ttjkst.mateInfo;

/**
 * Created by ttjkst on 2017/9/4.
 */
public interface PrvoiderMateInfoStore {
    boolean store(ProviderMateInfo mateInfo);
    boolean hasMate(ProviderMateInfo mateInfo);
    void delete(ProviderMateInfo mateInfo);
    void changeMateInfoByName(ProviderMateInfo mateInfo);
    ProviderMateInfo findByClass(Class clazz);
    PrvoiderMateInfoStore build();
}
