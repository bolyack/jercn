package com.bamboo.basic.jdk8.feature_22_action_usage;

/**
 * @Deacription 交易
 * @Author bamboo
 * @Date 2020/1/15 16:51
 * @Version 1.0
 **/
public class StreamFunctionTrade {

    private String name;
    private String city;

    public StreamFunctionTrade(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "StreamFunctionTrade{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
