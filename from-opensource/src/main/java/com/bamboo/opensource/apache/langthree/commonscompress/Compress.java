package com.bamboo.opensource.apache.langthree.commonscompress;

import java.io.File;
import java.io.IOException;

public interface Compress {

    int bufferLen = 1024;

    void doCompress(File srcFile, File destFile) throws IOException;

    void doDecompress(File srcFile, File destDir) throws IOException;

}