package org.github.ttjkst.JdkProxyTest;

import org.junit.Test;

import java.io.*;

/**
 * Created by ttjkst on 2017/9/6.
 */
public class FilePrintTest {

    @Test
    public void  test() throws IOException {
        FileInputStream inputStream =null;
        try {
            inputStream = new FileInputStream("C:\\Users\\ttjkst\\Desktop\\Server\\src\\main\\java\\org\\github\\ttjkst\\server\\customer\\proxy\\JDK\\ProxyJdkFactory.java");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedReader.lines().filter(x->!x.trim().equals("")).forEach(x->System.out.println(x));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
}
