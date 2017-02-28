package com.bamboo.utils.blockchain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by admin on 2017/2/28.
 */
public class UserEntity {

    @IgnoreAttr
    private int id;
    private String name;
    private int age;
    private String gender;
    private Date borthDay;
    private BigDecimal salary;
    private double avgGrade;
    private Double weight;
    private Long walkCount;
    private long course;
    private Integer englishScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getWalkCount() {
        return walkCount;
    }

    public void setWalkCount(Long walkCount) {
        this.walkCount = walkCount;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }

    public Integer getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(Integer englishScore) {
        this.englishScore = englishScore;
    }
}
