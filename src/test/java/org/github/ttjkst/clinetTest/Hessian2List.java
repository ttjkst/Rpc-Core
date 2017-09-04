package org.github.ttjkst.clinetTest;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttjkst on 2017/8/30.
 */
public class Hessian2List {

    @Test
    public void test() throws IOException {
        List<Object> array = new ArrayList<>();
        array.add("sss");
        array.add("mmm");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        Hessian2Output output = new Hessian2Output(byteArrayOutputStream);
        output.writeObject(array);
        output.flush();
        output.close();
        ByteArrayInputStream is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Hessian2Input input = new Hessian2Input(is);
       System.out.println(input.readObject());
    }
}
