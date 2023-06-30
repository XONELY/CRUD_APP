package org.xonely.controller;

import org.xonely.model.Label;
import org.xonely.model.Post;
import org.xonely.repository.gson.GsonLabelRepoImpl;

import java.util.*;

public class LabelController {
    GsonLabelRepoImpl glri = new GsonLabelRepoImpl();
    List<Label> importedLabels = glri.getAll();
    private final Scanner scanner = new Scanner(System.in);


    private int lastId = importedLabels.size();

    public void addLabel(Post post) {
        System.out.print("Задайте имя тэга: ");
        String labelName = scanner.next();
        importedLabels = glri.getAll();
        Label label = new Label(lastId,labelName);
        if (importedLabels.stream().noneMatch(streamW -> streamW.equals(label))) {
            post.AddLabel(label);
            glri.save(label);
        }
    }
    public List<Label> getLabels() {
        return glri.getAll();
    }


    public void showAllLabels(){
        importedLabels.forEach(System.out::println);
    }
    public void deleteLabel() {
        glri.getAll().forEach(System.out::println);
        System.out.println("Введите id тэга для удаления: ");
        int id = scanner.nextInt();
        glri.deleteById(id);
    }

    public void updateLabel(Label label) {
        glri.update(label);
    }

}
