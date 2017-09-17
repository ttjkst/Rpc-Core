package org.github.ttjkst.server.connector.process.Hessian2;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.github.ttjkst.packages.MessagePackage;
import org.github.ttjkst.server.connector.process.MethodExcutorProcess;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ttjkst on 2017/9/1.
 */
public class Hessian2RestExcutorProcess implements MethodExcutorProcess {
    private String serverUrl = "";
    @Override
    public Object excute(MessagePackage msgPackage, String className, String methodName,String version,int timeout) {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(timeout);
        httpRequestFactory.setConnectTimeout(timeout);
        httpRequestFactory.setReadTimeout(timeout);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        return restTemplate.execute(serverUrl+"/"+className+"/"+methodName+"/"+version, HttpMethod.POST,request->{
            request.getHeaders().set("content-type","octet-stream");
            Hessian2Output output = new Hessian2Output(request.getBody());
            output.writeObject(msgPackage);
        },response -> {
            try {
                Hessian2Input input = new Hessian2Input(response.getBody());
                return input.readObject();
            } catch (Exception e){
                throw  e;
            }finally {
                response.getBody().close();
            }
        });
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public Hessian2RestExcutorProcess setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return  this;
    }
}
