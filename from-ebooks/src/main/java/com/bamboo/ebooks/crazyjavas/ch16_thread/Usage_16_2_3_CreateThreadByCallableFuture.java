package com.bamboo.ebooks.crazyjavas.ch16_thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by bamboo on 2017/3/30.
 */
public class Usage_16_2_3_CreateThreadByCallableFuture {

    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            Usage_16_2_3_CallableFuture callableFuture = new Usage_16_2_3_CallableFuture(i);

            FutureTask<Map<String, Object>> futureTask = new FutureTask<Map<String, Object>>(callableFuture);

            new Thread(futureTask, "线程" + i).start();

            try {
                Map<String, Object> map = futureTask.get();
                System.out.println(map.get("threadName") + ";;;;" + map.get("result"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

class Usage_16_2_3_CallableFuture implements Callable<Map<String, Object>> {

    private int num;

    public Usage_16_2_3_CallableFuture(int nm) {
        this.num = nm;
    }

    public Map<String, Object> call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        if (num % 2 == 0) {
            throw new Exception("num除以2求余异常....");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("threadName", Thread.currentThread().getName());
        map.put("result", (num + 2 )* 2);
        return map;
    }
}