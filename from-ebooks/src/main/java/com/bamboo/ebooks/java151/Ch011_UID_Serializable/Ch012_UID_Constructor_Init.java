package com.bamboo.ebooks.java151.Ch011_UID_Serializable;

public class Ch012_UID_Constructor_Init {

    public static void main(String[] args) {
        PersonV3 personV3 = new PersonV3();

//        SerializationUtil.writeObject(personV3);

        Object retObj = SerializationUtil.readObject();
        System.out.println(((PersonV3)retObj).name);

    }

}
