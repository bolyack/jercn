package com.bamboo.basic.jdk8.feature_04_method_construct_ref;

/**
 * Created by bamboo on 2019-10-27.
 */
public class Person {

    private String firstName;
    private String lastName;

    public Person(){}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return firstName + " - " + lastName;
    }

}
