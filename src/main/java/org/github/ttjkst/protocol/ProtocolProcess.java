package org.github.ttjkst.protocol;

import org.github.ttjkst.packages.MessagePackage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ttjkst on 2017/8/30.
 */
public interface ProtocolProcess {
    MessagePackage processFromInputStrem(InputStream inputStream) throws IOException;
    MessagePackage processFromInputStrem(byte[] bytes) throws IOException;
}
