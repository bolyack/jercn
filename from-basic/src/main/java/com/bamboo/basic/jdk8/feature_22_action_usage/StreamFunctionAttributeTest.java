package com.bamboo.basic.jdk8.feature_22_action_usage;

import com.bamboo.utils.date.JodaTimeUtil;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @program: jercn
 * @description: test
 * @author: xdj
 * @create: 2020-02-17
 **/
public class StreamFunctionAttributeTest {


    public static void main(String[] args) {
//        List<StreamFunctionAttribute> list = new ArrayList<>();
//        list.add(new StreamFunctionAttribute(1L, "bamboo", 12));
//        list.add(new StreamFunctionAttribute(2L, "mongy", 32));
//        list.add(new StreamFunctionAttribute(3L, "tyyy", 45));
//        list.add(new StreamFunctionAttribute(4L, "opp", 31));
//        list.add(new StreamFunctionAttribute(5L, "ljw", 77));
//
//        Map<Long, String> maps =  list.stream().collect(Collectors.toMap(StreamFunctionAttribute::getMmpId, StreamFunctionAttribute::getName));
//        System.out.println(maps.get(4L));


        StreamFunctionAttributeTest t = new StreamFunctionAttributeTest();
        t.dd();

    }

    public void dd() {
        ThreadPoolExecutor executors = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());

        List<TaskRunable> tasks = new ArrayList<>();
        tasks.add(new TaskRunable(1, 1));
        tasks.add(new TaskRunable(2, 1));
        tasks.add(new TaskRunable(3, 1));
        tasks.add(new TaskRunable(4, 1));
        tasks.add(new TaskRunable(5, 1));
        try {
            List<Future<List<Map<String, Object>>>> f = executors.invokeAll(tasks);
            System.out.println(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        //关闭线程池
        executors.shutdown();
    }


    class TaskRunable implements Callable<List<Map<String, Object>>> {

        private int start;
        private int end;

        public TaskRunable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Map<String, Object>> call() throws Exception {
            String key = JodaTimeUtil.formatDate(new Date(System.currentTimeMillis()), JodaTimeUtil.SDF_YMDHMS);
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map = new HashMap();
            int num = (int)(Math.random()*10 + 1);
            System.out.println(num);
            Thread.sleep(num * 1000);
            map.put(key, JodaTimeUtil.formatDate(new Date(System.currentTimeMillis()), JodaTimeUtil.SDF_YMDHMS));
            list.add(map);
            return list;
        }
    }

}
