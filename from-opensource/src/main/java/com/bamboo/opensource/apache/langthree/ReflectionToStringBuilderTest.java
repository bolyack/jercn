package com.bamboo.opensource.apache.langthree;

import com.bamboo.opensource.apache.langthree.domain.Person;
import com.bamboo.opensource.apache.langthree.domain.PersonToStringFomratOverride;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Apache commons-lang3 ReflectionToString usage
 * Created by admin on 2017/3/9.
 * dependency Apache commons-lang3 3.5 jar
 */
public class ReflectionToStringBuilderTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("boo");
        person.setPassword("1234wEEXa");
        person.setBankNo("622609012304235560");
        person.setAge(26);
        person.setScore(98);
        person.setBorthDay(new Date());
        person.setHeight(100d);
        person.setWeight(101d);
        person.setMarry(true);
        person.setSmoker(false);
        person.setRunMile(1000l);
        person.setWalkMile(3000l);
        person.setSalary(new BigDecimal(3940.5));
        System.out.println(person.toString());

        //打印对象toString()字符串，默认不打印transient属性的值
        System.out.println(ToStringBuilder.reflectionToString(person));
        //打印对象toString()字符串，格式化日期等特殊属性，默认不打印transient属性的值
        System.out.println(ToStringBuilder.reflectionToString(person, new MyStyle()));
        //打印对象toString()字符串， 格式化日期等特殊属性，并打印transient属性的值
        System.out.println(ToStringBuilder.reflectionToString(person, new MyStyle(), true));

        System.out.println();
        //打印对象toString()字符串，默认不打印transient属性的值
        System.out.println(ReflectionToStringBuilder.toString(person));
        //打印对象toString()字符串，默认不打印transient属性的值, 但自定义对象中日期属性的输出样式
        System.out.println(ReflectionToStringBuilder.toString(person, new MyStyle()));
        //打印对象toString()字符串， 格式化日期等特殊属性，并打印transient属性的值
        System.out.println(ReflectionToStringBuilder.toString(person, new MyStyle(), true));

        /**
         * 通过 ReflectionToStringBuilder 子类 , 覆盖其 accept 方法来加以筛选, 如PersonToStringFomratOverride中toString()方法
         *      public String toString() {

                     return (new ReflectionToStringBuilder(this) {

                          protected boolean accept(Field f) {

                             return super.accept(f) && !f.getName().equals(“password”);

                     }}).toString();
                }
         *
         */
        System.out.println("=====================================================");
        PersonToStringFomratOverride personFO = new PersonToStringFomratOverride();
        personFO.setName("boo");
        personFO.setPassword("1234wEEXa");
        personFO.setBankNo("622609012304235560");
        personFO.setAge(26);
        personFO.setScore(98);
        personFO.setBorthDay(new Date());
        personFO.setHeight(100d);
        personFO.setWeight(101d);
        personFO.setMarry(true);
        personFO.setSmoker(false);
        personFO.setRunMile(1000l);
        personFO.setWalkMile(3000l);
        personFO.setSalary(new BigDecimal(3940.5));
        System.out.println(personFO.toString());

    }

}
