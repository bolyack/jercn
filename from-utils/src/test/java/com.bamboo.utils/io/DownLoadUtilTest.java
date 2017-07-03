package com.bamboo.utils.io;

import junit.framework.TestCase;

import java.io.*;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
public class DownLoadUtilTest extends TestCase {

    public void testDownLoad() throws Exception {
        String url = "http://fjs-vpc-oss-pub.oss-cn-hangzhou.aliyuncs.com/guide/crm_mobile_guide_v1.3.0.pdf";
        InputStream in = DownLoadUtil.downLoadFileForByteInputStream(new URL(url));
        File file = new File("test.pdf");
        if (!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        in.close();
    }


}
