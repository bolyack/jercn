package com.bamboo.ebooks.java151.Ch011_UID_Serializable;

import java.io.*;

public class SerializationUtil {

    private static final String FILE_NAME = "D:/obj.bin";

    /**
     * 序列化
     * @param s
     */
    public static void writeObject(Serializable s) {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(s);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     * @return
     */
    public static Object readObject() {
        Object obj = null;
        try {
            ObjectInput in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            obj = in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
