package com.bamboo.test.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectObj_100_Test {

    public static void main(String[] args) throws Exception {
        Class clazz = ReflectObj.class;
        ReflectObj ro = (ReflectObj) clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            //---只获取属性Field的名字（或属性都是public的修饰符），不需要加下面行
            f.setAccessible(true);
            System.out.println("属性修改前:" + f.getName() + " --> " + f.get(ro));
            //----final修饰的属性---不能修改值----
            if (!Modifier.isFinal(f.getModifiers())) {
                f.set(ro, (int)f.get(ro) + 1);
                System.out.println("属性修改后:" + f.getName() + " --> " + f.get(ro) + "\n");
            } else {
                System.out.println("属性注意:" +  f.getName() + "， 是final修饰，故不能修改\n");
            }
        }

        System.out.println("\n======================================\n\n");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("方法" + m.getName());
        }
        System.out.println("\n======================================\n\n");

        Method ageMethod = clazz.getDeclaredMethod("getAgeMethod");
        ageMethod.setAccessible(true);
        System.out.println("getAgeMethod()--返回值 --> " + ageMethod.invoke(ro));


    }

}
