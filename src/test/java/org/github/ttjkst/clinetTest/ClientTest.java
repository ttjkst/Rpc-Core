package org.github.ttjkst.clinetTest;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.google.common.io.ByteStreams;
import org.github.ttjkst.packages.MessagePackage;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by ttjkst on 2017/8/30.
 */
public class ClientTest {

    @Test
    public  void sendObjectByHesssian2(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.execute("http://localhost:8080/serverf/excutor/helloService/say/", HttpMethod.POST,request -> {
                    List<Object> args = new ArrayList<>();
                    args.add(1);
                    args.add(2);
                    MessagePackage msgPackage = new MessagePackage();
                    msgPackage.setValues(args.toArray());
                    Class[] classes = new Class[2];
                    classes[0]=int.class;
                    classes[1]= Integer.class;
                    msgPackage.setTypes(classes);
                    Hessian2Output hessian2Output = null;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
                    try {
                        hessian2Output = new Hessian2Output(byteArrayOutputStream);
                        hessian2Output.writeObject(msgPackage);
                        hessian2Output.flush();
                        request.getHeaders().add("content-type","octet-stream");
                        byteArrayOutputStream.writeTo(request.getBody());
                        request.getBody().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(hessian2Output!=null) {
                            request.getBody().close();
                            hessian2Output.close();
                        }
                    }
                },
                response -> {
                    Map<String,Object> map = new HashMap<>();
                    InputStream body = response.getBody();
                    byte[] bytes = ByteStreams.toByteArray(body);
                    System.out.println(new String(bytes));
                    return map;
                });
    }
}
