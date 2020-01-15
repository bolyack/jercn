package com.bamboo.basic.jdk8.feature_22_action_usage;

/**
 * @Deacription 交易
 * @Author bamboo
 * @Date 2020/1/15 16:51
 * @Version 1.0
 **/
public class StreamFunctionTrade {

    private String name;
    private String school;

    public StreamFunctionTrade(String name, String school) {
        this.name = name;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "StreamFunctionTrade{" +
                "name='" + name + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
