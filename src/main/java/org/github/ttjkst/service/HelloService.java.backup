package org.github.ttjkst.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ttjkst on 2017/8/25.
 */

@Service("helloService")
public class HelloService {

    public  String say(){
        System.out.println("excuting .....");
        return "helloWorld";
    }

    public String say(int x,int y){
        System.out.println("excuting .....");
        return (x+y)+"";
    }

    public Map<String,Object> say(String x, String m){
        Map<String,Object> map = new HashMap<>();
        map.put(x,m);
        map.put(m,"hell");
        return map;
    }

    public int say(BigDecimal s,Integer x){
        return s.add(new BigDecimal(x+"")).intValue();
    }

   /* public String say(Integer x,Integer y){
        return x+y+""+"Integer";
    }*/
}
