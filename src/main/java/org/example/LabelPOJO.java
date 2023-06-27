package org.example;

import java.io.Serializable;

public class LabelPOJO implements Serializable {
    private final int id = idCounter+1;
    private static int idCounter = 0;
    {idCounter +=1;}


    private final String name;
    public LabelPOJO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id+" "+name;

    }
}
