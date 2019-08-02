package com.bamboo.basic.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * ArrayBlockingQueue 一个基于数组实现有界阻塞队列
 *     ArrayBlockingQueue是采用数组实现的有界阻塞线程安全队列。如果向已满的队列继续塞入元素，将导致当前的线程阻塞。如果向空队列获取元素，那么将导致当前线程阻塞。
 * @See https://www.jianshu.com/p/2c9e4abffb04
 * @See https://blog.51cto.com/janephp/2417270
 * @See https://cloud.tencent.com/developer/article/1350854
 */
public class ArrayBlockingQueueTest0100 {

    public Logger logger = Logger.getLogger(ArrayBlockingQueueTest0100.class.getName());

    //---初始化指定容量的数组，初始化非公平的重入锁--(插入或移除时，按FIFO顺序处理)
    private static BlockingQueue<Food> queues = new ArrayBlockingQueue<Food>(2);

    class Food {

        private String name;

        public Food(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return " Food [name=" + this.name + "]";
        }
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            Food food = new Food("banana");
            try {
                //--无限阻塞--如果向已满的队列继续塞入元素，将导致当前的线程阻塞--
                queues.put(food);
                logger.info(Thread.currentThread().getName() + "-provider : " + food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Conumser implements Runnable {

        @Override
        public void run() {
            try {
                //--无限阻塞--如果向空队列获取元素，那么将导致当前线程阻塞。--
                Food food = queues.take();
                logger.info(Thread.currentThread().getName() + "-consumer : " + food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //---队列容量为2，这里添加5个线程，其实添加第二个线程后，队列阻塞
        int i = 0;
        for (i = 0; i < 5; i++) {
            new Thread(new ArrayBlockingQueueTest0100().new Producer()).start();
        }

        //---当前线程休眠后---队列还在阻塞中---
        Thread.sleep(10000L);

        while ( i > 0) {
            //---循环消费队列中的线程，这时队列容量空缺，则前面阻塞的线程继续添加到队列中
            new Thread(new ArrayBlockingQueueTest0100().new Conumser()).start();
            Thread.sleep(1500L);
            i--;
        }
    }


/**
 *      [操作]       [抛出异常]        [返回指定的值]      [无限阻塞]        [超时阻塞]
 *      插入          add(e)              offer(e)            put(e)           offer(e, time, unit)
 *      移除          remove(e)           poll()              take()           poll(time, unit)
 *      检查          element()           peek()              不提供           不提供
 */

}
