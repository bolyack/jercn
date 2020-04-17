package com.bamboo.basic.jdk8.feature_22_action_usage;

/**
 * @program: jercn
 * @description: 测试
 * @author: xdj
 * @create: 2020-02-17
 **/
public class StreamFunctionAttribute {

    private Long mmpId;

    private String name;
    private Integer age;

    public StreamFunctionAttribute(Long mmpId, String name, Integer age) {
        this.mmpId = mmpId;
        this.name = name;
        this.age = age;
    }

    public Long getMmpId() {
        return mmpId;
    }

    public void setMmpId(Long mmpId) {
        this.mmpId = mmpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
