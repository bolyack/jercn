package com.bamboo.ebooks.java151.Ch011_UID_Serializable;

import java.io.Serializable;

public class PersonV1 implements Serializable {

    private static final long serialVersionUID = -9065659304401531206L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
