package org.xonely.model;

import java.io.Serializable;
import java.util.*;

public class Post implements Serializable {
    private final int id;
    private List<String> content;
    private final String created;
    private String updated = "Неизвестно";
    private final List<Label> labels;
    private Status status;

    public Post(int id, List<String> content, String created) {
        this.id = id;
        this.created = created;
        this.labels = new ArrayList<>();
        this.status = Status.UNDER_REVIEW;

        this.content = content;

    }

    public List<Label> getLabels() {
        return labels;
    }

    public void updateLabel(Label label) {
        int index = labels.indexOf(label);
        labels.set(index, label);
    }

    public void AddLabel(Label label) {
        labels.add(label);
    }

    public void updateContent(List<String> newContent) {
        content = newContent;
    }

    public void updateDate(String updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Дата создания: '" + created + '\'' +
                ", Дата изменения: '" + updated + '\'' +
                ", Статус поста: " + status +
                "\n Текст статьи:\n" +
                String.join("\n", content) + "\n" +
                "Тэги: " + labels;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
