package org.xonely;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class PostPOJO implements Serializable {
    private final int id = idCounter+1;
    private static int idCounter = 0;
    {idCounter +=1;}

    private List<String> content;
    private final String created;
    private String updated = "Неизвестно";
    private final List<LabelPOJO> labels = new ArrayList<>();
    private PostStatus status;

    public PostPOJO(List<String> content, String created, String label, PostStatus status) {
        this.content = content;
        this.created = created;
        this.labels.add(new LabelPOJO(label));
        this.status = status;
    }

    public List<LabelPOJO> getLabels() {
        return labels;
    }

    public void AddLabel(LabelPOJO label) {
        labels.add(label);
    }

    public void setContent(List<String> nContent) {
        content = nContent;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
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
