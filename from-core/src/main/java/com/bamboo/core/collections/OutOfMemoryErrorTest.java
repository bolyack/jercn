package com.bamboo.core.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * https://mp.weixin.qq.com/s/VqKjRX5bRPQC9-TiO5ByOA
 * 如果未实现 equals() 和 hashcode() ，则 Java 应用程序中可能会发生内存泄漏。
 *  考虑下面的一个小代码示例，其中如果未实现 equals() 和 hashcode() ，则 HashMap 保持引用处于活动状态。
 *  结果， HashMap 通过重复添加相同的键而不断增长，最后抛出 OutOfMemoryError 。
 */
public class OutOfMemoryErrorTest {

    private String id;

    public OutOfMemoryErrorTest(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Map<OutOfMemoryErrorTest, String> map = new HashMap<>();
        while(true) {
            map.put(new OutOfMemoryErrorTest("id"), "any value");
        }
    }

}
