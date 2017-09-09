package org.github.ttjkst.service;

import org.github.ttjkst.server.provider.annotation.ServerProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ttjkst on 2017/9/9.
 */
@ServerProvider(name="hello")
public class HelloImpl implements  IHello{

    @Autowired
    private SayTools tools;
    @Override
    public void say() {
        System.out.println(tools.say());
    }

    @Override
    public String say( String i ) {
        System.out.println(tools.say()+"hasArg");
        return tools.say();
    }
}
