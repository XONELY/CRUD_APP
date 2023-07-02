package org.xonely.model;

import java.io.Serializable;
import java.util.Objects;

public class Label implements Serializable {
    private final int id;
    private final String name;
    private Status status;

    public Label(int id, String name) {
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
        return id + " || " + name + " || " + status;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
