package com.bamboo.core.threads;

/**
 * https://mp.weixin.qq.com/s?__biz=MzIwNTI2ODY5OA==&mid=2649939179&idx=1&sn=5868587f841bfa8b792cc686311c758c&chksm=8f350f5eb8428648de1c2a1a50a43f01350c53922492bcda9677ffed4e1eb153cd5c79a7817f&scene=21#wechat_redirect
 * @program: jercn
 * @description: 多线程-->JMM Java内存模型
 * @link https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzIwNTI2ODY5OA==&action=getalbum&album_id=1575497111289266177&scene=173&from_msgid=2649939179&from_itemidx=1&count=3#wechat_redirect
 * @create: 2020-11-06
 **/
public class JmmUsage1001 {

//    public static volatile boolean running = true;
    public static boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread( () -> {
            long i = 0;
            System.out.println("thread start ");
            while (running) {
                i ++;
            }
            System.out.println("thread end " + i);
        }).start();
        Thread.sleep(1000);
        running = false;
        //  程序会一直运行下去，如果将属性running设置为valotle类型，那么线程是可以正常结束的。
    }

}
