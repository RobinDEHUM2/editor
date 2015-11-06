package org.ulco;

public class ID {
    static private int ID = 0;

    public static int newID (){
        ID++;
        return ID;
    }

    public static int oldID (){
        return ID;
    }
}