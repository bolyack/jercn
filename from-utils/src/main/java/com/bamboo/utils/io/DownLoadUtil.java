package com.bamboo.utils.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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


}
