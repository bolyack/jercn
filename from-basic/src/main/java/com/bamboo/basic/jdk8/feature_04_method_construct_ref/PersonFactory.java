package com.bamboo.basic.jdk8.feature_04_method_construct_ref;

/**
 * Created by bamboo on 2019-10-27.
 */
public interface PersonFactory<P extends Person> {

    P create(String firstName, String lastName);

}
