package com.bamboo.opensource.apache.langthree.Domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by admin on 2017/3/9.
 */
public class Person {
    private String name;
    private String password;
    private transient String bankNo;
    private int age;
    private Integer score;
    private boolean smoker;
    private Boolean marry;
    private double weight;
    private Double height;
    private long walkMile;
    private Long runMile;
    private Date borthDay;
    private BigDecimal salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public Boolean getMarry() {
        return marry;
    }

    public void setMarry(Boolean marry) {
        this.marry = marry;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public long getWalkMile() {
        return walkMile;
    }

    public void setWalkMile(long walkMile) {
        this.walkMile = walkMile;
    }

    public Long getRunMile() {
        return runMile;
    }

    public void setRunMile(Long runMile) {
        this.runMile = runMile;
    }

    public Date getBorthDay() {
        return borthDay;
    }

    public void setBorthDay(Date borthDay) {
        this.borthDay = borthDay;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

   /* @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", smoker=" + smoker +
                ", marry=" + marry +
                ", weight=" + weight +
                ", height=" + height +
                ", walkMile=" + walkMile +
                ", runMile=" + runMile +
                ", borthDay=" + borthDay +
                ", salary=" + salary +
                '}';
    }*/
}
