package com.bamboo.basic.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * PriorityQueue的逻辑结构是一棵完全二叉树，存储结构其实是一个数组。逻辑结构层次遍历的结果刚好是一个数组。
 * @See https://www.jianshu.com/p/f1fd9b82cb72
 * @See https://www.cnblogs.com/CarpenterLee/p/5488070.html
 *
 *   通过源码，也可看出PriorityQueue并不是线程安全队列，因为offer/poll都没有对队列进行锁定，所以，如果要拥有线程安全的优先级队列，需要额外进行加锁操作。
 *  【总结】
 *      1 > PriorityQueue是一种无界的，线程不安全的队列
 *      2 > PriorityQueue是一种通过数组实现的，并拥有优先级的队列
 *      3 > PriorityQueue存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器
 */
public class PriorityQueueUsageTest0010 {

    /**
     * PriorityQueue使用跟普通队列一样，唯一区别是PriorityQueue会根据排序规则决定谁在队头，谁在队尾。
     * 观察打印结果， 入列：12534， 出列是12345(若都是按照poll()方法)，
     *  也是说出列时做了相关判断，将最小的值返回。默认情况下PriorityQueue使用自然排序法，最小元素先出列。
     */
    public static void addEleByOffer() {
        PriorityQueue<String> queues = new PriorityQueue<>();
        queues.offer("1");
        queues.offer("2");
        queues.offer("5");
        queues.offer("3");
        queues.offer("4");
        System.out.println("添加队列结束....\n");

        String getAndDelHeadEle1 = queues.poll(); // 1
        System.out.println("获取并删除队列头元素：" + getAndDelHeadEle1);  // 此时队列里的数量已减少1个，同时重新计算(安装默认的比较器)新的队列头
        String getAndDelHeadEle2 = queues.poll(); // 2
        System.out.println("获取并删除新队列头元素：" + getAndDelHeadEle2); // 此时队列里的数量已减少1个，同时重新计算(安装默认的比较器)新的队列头
        String getAndDelHeadEle3 = queues.poll(); // 3
        System.out.println("获取并删除新队列头元素：" + getAndDelHeadEle3); // 此时队列里的数量已减少1个，同时重新计算(安装默认的比较器)新的队列头
        String queryUnDelEle = queues.peek(); // 4
        System.out.println("获取但未删除新队列头元素：" + queryUnDelEle); // 此时队列里的数量未发生变化
        System.out.println(1);//此处用debug调试
    }

    /**
     * 自定义排序规则
     */
    public static void addEleByOfferAndCustomCompare() {
        //通过改造器指定排序规则
        PriorityQueue<PriorityQueueUsageStudent> studentQuenes = new PriorityQueue<>(new Comparator<PriorityQueueUsageStudent>() {
            //按照分数低到高，分数相等按名字
            @Override
            public int compare(PriorityQueueUsageStudent o1, PriorityQueueUsageStudent o2) {
                if (o1.getScore() == o2.getScore()) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getScore() - o2.getScore();
            }
        });

        //入列
        studentQuenes.offer(new PriorityQueueUsageStudent("dafei", 20));
        studentQuenes.offer(new PriorityQueueUsageStudent("will", 17));
        studentQuenes.offer(new PriorityQueueUsageStudent("setf", 30));
        studentQuenes.offer(new PriorityQueueUsageStudent("bunny", 20));

        //出列
        System.out.println(studentQuenes.poll());  //Student{name='will', score=17}
        System.out.println(studentQuenes.poll());  //Student{name='bunny', score=20}
        System.out.println(studentQuenes.poll());  //Student{name='dafei', score=20}
        System.out.println(studentQuenes.poll());  //Student{name='setf', score=30}
    }

    public static void main(String[] args) {
//        addEleByOffer();
        addEleByOfferAndCustomCompare();
    }

}

class PriorityQueueUsageStudent {

    private String name;  //名字
    private int score;    //分数

    public PriorityQueueUsageStudent(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PriorityQueueUsageStudent{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

}
