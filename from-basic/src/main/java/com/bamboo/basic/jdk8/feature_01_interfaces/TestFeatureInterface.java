package com.bamboo.basic.jdk8.feature_01_interfaces;

/**
 * 测试jdk8新特性--接口的默认方法
 * Created by bamboo on 2019-10-27.
 */
public class TestFeatureInterface {

    public static void main(String[] args) {
        System.out.println("----jdk8新特性(接口默认方法)测试----");
        BaseInterface triangleITF = new TriangleCalculate();
        System.out.println("三角形求和(接口定义普通方法)===>" + triangleITF.addSum(4, 8));
        System.out.println("三角形平方值(接口默认方法)===>" + triangleITF.sqrt(4));

        BaseInterface squareITF = new SquareCalculate();
        System.out.println("正方形求和(接口定义普通方法)===>" + squareITF.addSum(2,2));
        System.out.println("正方形平方值(接口默认方法)===>" + squareITF.sqrt(4));
        System.out.println("接口中另一个默认的方法===>" + squareITF.anOtherMethod());

    }

}
