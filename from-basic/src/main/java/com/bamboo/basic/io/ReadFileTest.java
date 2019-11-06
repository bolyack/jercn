package com.bamboo.basic.io;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java按行读取文件
 * link https://www.iteye.com/blog/heipark-1721737
 * link https://blog.csdn.net/bryBLOG/article/details/77944206
 * link https://www.jianshu.com/p/cc006a504b9f
 *  总结：
 *      1） InputStreamReader是从字节流向字符流转化的桥梁：它读取字节用特定的字符集将它们decode成字符串。
 *          InputStreamReader可以处理其他非文件的流，比如网络连接、classpath资源、ZIP文件等。
 *      2） FileReader 是读取字符文件的便利类。该类的构造函数默认的字符集编码和默认的字节缓存大小是合适的。
 *          FileReader不允许您指定平台以外的编码。因此如果程序运行在不同的编码平台系统上，则不宜使用它。
 *  综上：InputStreamReader是比FileReader更安全的选择。
 */
public class ReadFileTest {

    /**
     * 通过构造InputStreamReader按行读取文件
     *      InputStreamReader是从字节流向字符流转化的桥梁：它读取字节用特定的字符集将它们decode成字符串。
     *      InputStreamReader可以处理其他非文件的流，比如网络连接、classpath资源、ZIP文件等
     * @param inputStream
     * @throws IOException
     */
    public static void readFileByInputStreamReader(InputStream inputStream) throws IOException {
        //Construct BufferedReader from InputStreamReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        System.out.println("=======================================读取文件(通过构造InputStreamReader按行读取)----start--------=======================================");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("=======================================读取文件(通过构造InputStreamReader按行读取)----end-----------=======================================");
        reader.close();
    }



    /**
     * 通过构造FileReader按行读取
     * @param file
     * @throws IOException
     */
    public static void readFileByFileReader(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        System.out.println("=======================================读取文件(通过构造FileReader按行读取)----start--------=======================================");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("=======================================读取文件(通过构造FileReader按行读取)----end----------=======================================");
    }

    /**
     * 通过jdk1.8  Files.newBufferedReader 按行读取文件
     * @param file
     */
    public static void readFileByJdk1_8(File file) {
        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get(file.toURI());
        //下面一行，这里如果指定的字符编码不对，可能会抛出异常 MalformedInputException ，或者读取到了乱码：java.nio.charset.MalformedInputException: Input length = 1
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = null;
            System.out.println("=======================================读取文件(通过Files.newBufferedReader按行读取)----end-----------=======================================");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("=======================================读取文件(通过Files.newBufferedReader按行读取)----end-----------=======================================");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
//        ClassLoader loader = ReadFileTest.class.getClassLoader();
//        InputStream inputStream = loader.getResourceAsStream("test_read_file.txt");
//        readFileByInputStreamReader(inputStream);

        URL url = ClassLoader.getSystemResource("test_read_file.txt");
        File file = new File(url.getFile());
//        readFileByFileReader(file);

        readFileByJdk1_8(file);
    }


}
