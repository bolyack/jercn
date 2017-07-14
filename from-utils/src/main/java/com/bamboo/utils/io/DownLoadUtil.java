package com.bamboo.utils.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * download file
 * Created by Administrator on 2017/7/3 0003.
 */
public class DownLoadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownLoadUtil.class);

    public static InputStream downLoadFileForByteInputStream(URL url) {
        DataInputStream in = null;
        try {
            if (null != url) {
                URLConnection connection = url.openConnection();
                in = new DataInputStream(connection.getInputStream());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return in;
    }

    public static ByteArrayOutputStream downLoadFileByByteStream(URL url) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataInputStream in = null;
        try {
            if (null != url) {
                URLConnection connection = url.openConnection();
                in = new DataInputStream(connection.getInputStream());
                byte[] buffer = new byte[4096];
                int count = 0;
                while ((count = in.read(buffer)) > 0) {
                    bos.write(buffer, 0, count);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (null != bos) bos.close();
                if (null != in) in.close();
            } catch (IOException ie) {
                LOGGER.error(ie.getMessage(), ie);
            }
        }
        return bos;
    }

    /* Spring mvc：online view image by ByteArrayOutputStream
    @RequestMapping("common/downLoad.html")
    public void fileLoad(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "二维码.jpg";
        try{
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type","image/jpeg");
        response.setHeader("Content-disposition", "fileName=" + fileName);

        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ByteArrayOutputStream bos = QRCodeUtil.encodeOutputStream("http://wwww.baidu.com?companyId=1&token=11123214242sdsa1", true);
            os.write(bos.toByteArray());
            os.flush();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (null != os) os.close();
            } catch (Exception e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
        }
    }*/

}
