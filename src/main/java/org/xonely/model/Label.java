package org.xonely.model;

import org.xonely.controller.LabelController;

import java.io.Serializable;

public class Label implements Serializable {
    private int id;
    private final String name;
    private Status status;

    LabelController labelController = new LabelController();

    public Label(int id,String name) {
        this.name = name;
        this.id = id;
        status = Status.UNDER_REVIEW;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id+" "+name;

    }

    public int getId() {
        return id;
    }
}
