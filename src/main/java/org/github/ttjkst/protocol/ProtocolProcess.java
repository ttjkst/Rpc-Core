package org.github.ttjkst.protocol;

import org.github.ttjkst.packages.MessagePackage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by ttjkst on 2017/8/30.
 */
public interface ProtocolProcess {
    MessagePackage processFromInputStrem(byte[] bytes) throws IOException;
    ByteArrayOutputStream processToBytes( Object src) throws IOException;
}
