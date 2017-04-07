package com.bamboo.utils.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

/**
 * @see  http://blog.csdn.net/gaowen_han/article/details/7163737/
 * @see http://www.cnblogs.com/scw2901/p/4379143.html
 * Created by admin on 2017/4/6.
 */
public class FileDeComUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileDeComUtil.class);

    public static void main(String[] args) throws Exception {
        File f = new File("C:\\Users\\admin\\Desktop\\prd_log\\zeus2017.tar.gz");

        System.out.println("Mime Type of " + f.getName() + " is " +
                new MimetypesFileTypeMap().getContentType(f));
    }

    //TODO 获取文件类型

    public static void doSuffix(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        String suffix = fileName.substring(fileName.lastIndexOf("."));
    }


}
