package com.bamboo.opensource.apache.langthree.commonscompress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

/**
 * Apache commons compress ZIP压缩
 * Apache commons-compress ZipArchive usage
 * dependency Apache commons-compress 1.18 jar
 */
public class CommonsZipCompress implements Compress {

    /**
     * 用于单文件Zip压缩
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    @Override
    public void doCompress(File srcFile, File destFile) throws IOException {
        ZipArchiveOutputStream out = null;
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(srcFile), bufferLen);
            out = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), bufferLen));
            ZipArchiveEntry entry = new ZipArchiveEntry(srcFile.getName());
            entry.setSize(srcFile.length());
            out.putArchiveEntry(entry);
            IOUtils.copy(in, out);
            out.closeArchiveEntry();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }


    /**
     * (遍历)解压缩ZIP文件(夹)
     * @param srcFile
     * @param destDir
     * @throws IOException
     */
    @Override
    public void doDecompress(File srcFile, File destDir) throws IOException {
        ZipArchiveInputStream in = null;
        try {
            in = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(srcFile), bufferLen));
            ZipArchiveEntry entry = null;
            while ((entry = in.getNextZipEntry()) != null) {
                if (entry.isDirectory()) {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                } else {
                    OutputStream out = null;
                    try {
                        out = new BufferedOutputStream(new FileOutputStream(new File(destDir, entry.getName())), bufferLen);
                        IOUtils.copy(in, out);
                    } finally {
                        IOUtils.closeQuietly(out);
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static void main(String[] args) throws Exception {
        CommonsZipCompress czc = new CommonsZipCompress();
//        File srcFile = new File("C:\\Users\\Administrator\\Desktop\\thor-ddl.sql");
//        File destFile = new File("C:\\Users\\Administrator\\Desktop\\aa.zip");
//        if (!destFile.exists()) {
//            destFile.createNewFile();
//        } else {
//            destFile.delete();
//            destFile.createNewFile();
//        }
//        czc.doCompress(srcFile, destFile);


//        File srcFile = new File("C:\\Users\\Administrator\\Desktop\\zz.zip");
//        File destFile = new File("C:\\Users\\Administrator\\Desktop\\aa\\");
//        czc.doDecompress(srcFile, destFile);
        //https://blog.csdn.net/inkfish/article/details/4900870
//        https://blog.csdn.net/mahoking/article/details/54882783
    }
}
