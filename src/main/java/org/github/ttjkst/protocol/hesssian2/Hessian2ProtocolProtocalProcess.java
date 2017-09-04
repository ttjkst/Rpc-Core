package org.github.ttjkst.protocol.hesssian2;

import com.caucho.hessian.io.Hessian2Input;
import org.github.ttjkst.packages.MessagePackage;
import org.github.ttjkst.protocol.ProtocolProcess;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ttjkst on 2017/8/30.
 */
public class Hessian2ProtocolProtocalProcess implements ProtocolProcess {
    @Override
    public MessagePackage processFromInputStrem(InputStream inputStream) throws IOException {
        Hessian2Input hessian2Input =null;
        try {
            hessian2Input = new Hessian2Input(inputStream);
            return (MessagePackage) hessian2Input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(hessian2Input!=null) {
                hessian2Input.close();
            }
        }
        throw new RuntimeException("serialize error!");
    }

    @Override
    public MessagePackage processFromInputStrem(byte[] bytes) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input =null;
        try {
            hessian2Input = new Hessian2Input(is);
            Object  o =hessian2Input.readObject();
            return (MessagePackage) o;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(hessian2Input!=null) {
                hessian2Input.close();
            }
        }
        throw new RuntimeException("serialize error!");
    }
}
