package com.bamboo.ebooks.java151.Ch011_UID_Serializable;

import java.io.Serializable;

public class PersonV3 implements Serializable {

    private static final long serialVersionUID = 6924490942037634636L;

    public final String name;

    public PersonV3() {
        name = "哈哈哈，我是构造赋值(改造聚)~~~";
    }

}
