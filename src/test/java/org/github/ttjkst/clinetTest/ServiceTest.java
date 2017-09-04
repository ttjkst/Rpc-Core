package org.github.ttjkst.clinetTest;

import org.github.ttjkst.service.HelloService;
import org.junit.Test;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class ServiceTest {

    @Test
    void  sayTest(){
        int i =1;
        int e =2;
        Integer m = 2;
        HelloService service = new HelloService();
        service.say(i,e);
        service.say(i,m);
    }
}
