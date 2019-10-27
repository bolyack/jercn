package com.bamboo.test.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectObj_100_Test extends Object {

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

        //----getDeclaredMethods 方法返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。----
        System.out.println("\n==================getDeclaredMethods====================");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("方法" + m.getName());
        }
        System.out.println("==================getDeclaredMethods====================\n\n");

        //-----通过 getMethods() 方法返回某个类的所有公用（public）方法，包括其继承类的公用方法。---
        System.out.println("\n==================getMethods====================");
        Method[] methodAs = clazz.getMethods();
        for (Method m : methodAs) {
            System.out.println("方法" + m.getName());
        }
        System.out.println("==================getMethods====================\n\n");

        //---getMethod 方法返回一个特定的方法，其中第一个参数为方法名称，后面的参数为方法的参数对应Class的对象。--
        System.out.println("\n==================getMethod()--getHighAge====================");
        Method highAgeMethod = clazz.getMethod("getHighAge", int.class);
        System.out.println("getAgeMethod()--返回值 --> " + highAgeMethod.invoke(ro, 12));
        System.out.println("==================getMethod()--getHighAge====================\n\n");


    }

}
