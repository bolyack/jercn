package com.bamboo.utils.ali;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 演示常用功能
 * @see /https://help.aliyun.com/document_detail/32009.html?spm=5176.doc32011.6.659.tj031y
 * @see /http://grepcode.com/file/repo1.maven.org/maven2/com.aliyun.oss/aliyun-sdk-oss/2.0.0/com/aliyun/oss/OSSErrorCode.java
 * Created by Administrator on 2017/8/2 0002.
 */
public class AliDemoOssUtils {

    private static final Logger logger = LoggerFactory.getLogger(AliDemoOssUtils.class);

    private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAI0zcwsfjTNhDWhs";
    private static String accessKeySecret = "D07idCi7sPjfTFDy3gaXvC8299bHwwBq";


    public static String prefix = "test1/test2/oss-java-sdk-";
    public static String suffix = ".txt";
    private static String Topic_Prefix;



    private static String bucketName;

    private static String aliyunOssUrl;

    private static final String OSS_FILE_SEPARATOR = "/";


    public static void main(String[] args) throws Exception {
        OSSClient ossClient = init();


//        upload(ossClient, "akcm-vpc-oss-pub", prefix + suffix); //downLoad
//        downLoad(ossClient, "akcm-vpc-oss-pub", prefix + suffix);  //deleteFile
//        deleteFile(ossClient, "akcm-vpc-oss-pub", prefix + suffix);  //deleteFile
//        bacthUploadFile(ossClient, "akcm-vpc-oss-pub");  //bacthUploadFile
//        bacthDeleteFile(ossClient, "akcm-vpc-oss-pub");  //bacthDeleteFile
    }

    public static OSSClient init() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        BucketInfo f = ossClient.getBucketInfo("akcm-vpc-oss-pub");
        return ossClient;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 上传文件
     *
     * @param ossClient
     * @param bucketName
     * @param key
     * @throws IOException
     */
    public static void upload(OSSClient ossClient, String bucketName, String key) throws IOException {
        try {
            System.out.println("Uploading a new object to OSS from a file\n");
            ossClient.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ossClient.shutdown();
        }
    }
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile(prefix, suffix);
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz");
        writer.write("0123456789011234567890");
        writer.close();

        return file;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 下载以打印字符串形式
     *
     * @param ossClient
     * @param bucketName
     * @param key
     * @throws IOException
     */
    public static void downLoad(OSSClient ossClient, String bucketName, String key) throws IOException {
        System.out.println("Downloading an object");
        OSSObject object = ossClient.getObject(bucketName, key);
        System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
        displayTextInputStream(object.getObjectContent());
    }
    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();

        reader.close();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 删除单个文件
     *
     * @param ossClient
     * @param bucketName
     * @param key
     * @throws IOException
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String key) throws Exception {
        try {
            ossClient.deleteObject(bucketName, prefix + suffix);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            ossClient.shutdown();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 批量上传文件
     *
     * @param ossClient
     * @param bucketName
     */
    public static void bacthUploadFile(OSSClient ossClient, String bucketName) {
        final String content = "Thank you for using Aliyun Object Storage Service";
        final String keyPrefix = "test/MyObjectKey";
        List<String> keys = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            String key = keyPrefix + i;
            InputStream instream = new ByteArrayInputStream(content.getBytes());
            ossClient.putObject(bucketName, key, instream);
            System.out.println("Succeed to put object " + key);
            keys.add(key);
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 批量删除
     * @param ossClient
     * @param bucketName
     */
    public static void bacthDeleteFile(OSSClient ossClient, String bucketName) {
        System.out.println("\nDeleting all objects:");
        final String keyPrefix = "test/MyObjectKey";
        List<String> keys = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            String key = keyPrefix + i;
            keys.add(key);
        }
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(
                new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        for (String object : deletedObjects) {
            System.out.println("\t" + object);
        }
    }


    /**
     * 上传附件
     * @param fileName
     * @param in
     * @return
     */
    public static String uploadImag(String fileName, InputStream in) {
        OSSClient ossClient = null;
        try {
            ossClient = init();
            Date date = new Date();

            noExistCreateDir(ossClient, Topic_Prefix + OSS_FILE_SEPARATOR);

            String keyUrl = Topic_Prefix + OSS_FILE_SEPARATOR + date.getTime();
            String suffix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);

            logger.info("start upload!");
            ossClient.putObject(new PutObjectRequest(bucketName, keyUrl + suffix, in));
            logger.info("End upload!");
            String picUrl = aliyunOssUrl + keyUrl + suffix;

            return picUrl;
        } finally {
            if (null != ossClient)
                ossClient.shutdown();
        }
    }


    /**
     * 判断OSS是否存在目录，不存在就创建一个
     * @param ossClient
     * @param dirPath
     * @return
     */
    public static void noExistCreateDir(OSSClient ossClient, String dirPath) {
        // Object是否存在   https://help.aliyun.com/document_detail/32015.html?spm=a2c4g.11186623.2.7.tDzInl  Object是否存在
        boolean found = ossClient.doesObjectExist(bucketName, dirPath);
        if (!found) {
            final String keySuffixWithSlash = dirPath;
            // https://help.aliyun.com/document_detail/32013.html?spm=a2c4g.11186623.2.5.My8H92  创建模拟文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            // https://help.aliyun.com/document_detail/32015.html?spm=a2c4g.11186623.2.7.tDzInl  设置Object ACL
            ossClient.setObjectAcl(bucketName, keySuffixWithSlash, CannedAccessControlList.PublicRead);
        }
    }

}
