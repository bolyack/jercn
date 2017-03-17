package com.bamboo.utils.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by admin on 2017/3/14.
 */
public class FileOperUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileOperUtils.class);

    /**
     *  Create a file and include its parent directory
     * @param fileName
     * @return
     * @throws Exception
     */
    public static File createFile(String fileName) throws Exception {
        File file = new File(fileName);
        if (null != file.getParent()) {
            File dirFile = new File(file.getParent());
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        logger.info(file.getAbsolutePath());
        return file;
    }

    /**
     * The file input stream is written to the new file
     * @param in
     * @param outFile
     * @throws Exception
     */
    public static void writeFile(InputStream in, File outFile) throws Exception {
        FileOutputStream fos = new FileOutputStream(outFile);

        byte[] b = new byte[1024];
        while((in.read(b)) != -1){
            fos.write(b);
        }

        in.close();
        fos.close();
    }

}
