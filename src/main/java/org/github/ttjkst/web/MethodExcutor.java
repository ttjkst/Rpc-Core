package org.github.ttjkst.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.caucho.hessian.io.Hessian2Output;
import com.google.common.io.ByteStreams;
import org.github.ttjkst.packages.MessagePackage;
import org.github.ttjkst.processes.TypeProcess;
import org.github.ttjkst.processes.TypeProcessFactory;
import org.github.ttjkst.protocol.ProtocolProcess;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ttjkst on 2017/8/24.
 */
@Controller
@RequestMapping("/excutor")
public class MethodExcutor implements ApplicationContextAware{
    public ApplicationContext applicationContext;

    @Autowired
    public ProtocolProcess process;
    @RequestMapping("/{className}/{methodName}")
    public ResponseEntity<Object> excute(@PathVariable("className")String className, @PathVariable("methodName")String methodName,HttpServletRequest request) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException,IOException{
        Object excuter = applicationContext.getBean(className);
        MessagePackage msgPackage = null;
        try {
            byte[] bytes = ByteStreams.toByteArray(request.getInputStream());
            msgPackage   = process.processFromInputStrem(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(request.getInputStream()!=null){
                request.getInputStream().close();
            }
        }
        Method method = excuter.getClass().getMethod(methodName,msgPackage.getTypes());
        return new ResponseEntity<Object>(method.invoke(excuter,msgPackage.getValues()),HttpStatus.OK);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
