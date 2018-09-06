package com.bamboo.opensource.apache.langthree.commonscompress;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

/**
 * Apache commons compress ZIP压缩
 * Apache commons-compress GZIPCompress usage
 * dependency Apache commons-compress 1.18 jar
 */
public class CommonsGZIPCompress implements Compress {


    @Override
    public void doCompress(File srcFile, File destFile) throws IOException {
        OutputStream out = null;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);
            out = new BZip2CompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));
            IOUtils.copy(is, out);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    @Override
    public void doDecompress(File srcFile, File destDir) throws IOException {
        OutputStream out = null;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);
            out = new BZip2CompressorOutputStream(new BufferedOutputStream(new FileOutputStream(destDir), bufferLen));
            IOUtils.copy(is, out);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }
}
