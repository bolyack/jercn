package com.bamboo.ebooks.java151.Ch011_UID_Serializable;

/**
 *  11：养成良好的习惯，显式声明UID
 */
public class Ch011_UID_Test {


    public static void testScene1() {
        PersonV1 personV1 = new PersonV1();
        personV1.setName("哈哈，您好，我是测试内容!!~~");

        //序列化--生成的时候，指定PersonV1的UID=-9065659304401531206L
//        SerializationUtil.writeObject(personV1);

        //反序列化---反向读取时把PersonV1的UID=-9065659304401531202L，就是不一致时
        //报java.io.InvalidClassException异常
        Object retObj = SerializationUtil.readObject();
        System.out.println(((PersonV1)retObj).getName());
    }

    public static void testScene2() {
        PersonV2 personV2 = new PersonV2();
        personV2.setName("哈哈，您好，我是测试内容-升级了，忘记重新计算修改serialVersionUID的值!!~~");
//        personV2.setAge(26);

        //生产者方-序列化--PersonV2虽然增加age属性，但由于疏忽、升级时间窗口不一致等，导致serialVersionUID的未修改
//        SerializationUtil.writeObject(personV2);

        //服务调用方-反序列化
        Object retObj = SerializationUtil.readObject();
        System.out.println(1);
//        System.out.println(((PersonV1)retObj).getName());//本机不能重现跨服务调用的序列化，这里报错
        //实际效果是 这里获取的对象，缺少age属性值。



        //或者--在生产者方第一次使用PersonV2时给name、age属性赋值，然后序列化到硬盘上，然后把age属性去掉后
        //再反序列的时候，对象里就只有name属性及值，其实序列化文件里有age属性及值。

    }


    public static void main(String[] args) {
        testScene2();
    }

}
