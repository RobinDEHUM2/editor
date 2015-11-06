package org.ulco;

public class ID {
    static private int ID = 0;

    private static ID INSTANCE = new ID();

    private ID(){}

    public static ID getInstance(){
        return INSTANCE;
    }


    public static int newID (){
        ID++;
        return ID;
    }

    public static int oldID (){
        return ID;
    }
}