package com.bamboo.basic.jdk8.feature_01_interfaces;

/**
 * 正方形计算
 *      实现了BaseInterface接口的子类只需要实现一个addSum方法，默认方法sqrt将在子类上可以直接使用。
 * Created by bamboo on 2019-10-27.
 */
public class SquareCalculate implements BaseInterface {

    @Override
    public double addSum(int a, int b) {
        return (a + b) * 2;
    }

}
