package org.xonely.model;

import org.xonely.controller.LabelController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Post implements Serializable {
    private int id;
    private List<String> content;
    private String created;
    private String updated = "Неизвестно";
    private List<Label> labels;
    private Status status;

    public Post(int id, List<String> content, String created) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.labels = new ArrayList<>();
        this.status = Status.UNDER_REVIEW;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void AddLabel(Label label) {
        labels.add(label);
    }

    public void updateContent(List<String> nContent) {
        content = nContent;
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
        return "Статья № " + id +
                ", Дата создания: '" + created + '\'' +
                ", Дата изменения: '" + updated + '\'' +
                ", Тэги: " + labels +
                ", Статус: " + status +
                "\n Текст статьи:\n" +
                content.toString() + "\n";
    }
}
