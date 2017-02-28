package com.bamboo.utils.blockchain;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/2/28.
 * 区块链相关工具类
 */
public class BlockChainUtils {

    /**
     * 实例对象的属性转化为String数组(若属性标记ignoreAnnotationClass注解，则不转化; 如果属性为null，则转化为字符串) 如下：
     *      User u = new User();
     *      u.setName("aaa");
     *      u.setGender(null);
     *      u.setAge(15)
     *  转化后
     *      new String[]{"name", "aaa", "gender", "", "age", "15"}
     * @param instance 实例对象
     * @param ignoreAnnotationClass 忽略实例对象标注ignoreAnnotationClass注解的属性
     * @return String[] 字符数组
     * @throws Exception
     */
    public static String[] parseObj2StringArray(Object instance, Class ignoreAnnotationClass) throws Exception {
        List<String> list = new ArrayList<String>();
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if(!field.isAnnotationPresent(ignoreAnnotationClass)) {
                Class typeClazz = field.getType();
                Object val = field.get(instance);
                list.add(field.getName());
                if (null == val) {
                    list.add("");
                } else {
                    if (typeClazz == Date.class) {
                        Date localDate = (Date) val;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        list.add(sdf.format(localDate));
                    }  else {
                        list.add(val.toString());
                    }

                }
            }
        }
        return list.toArray(new String[list.size()]);
    }


}
