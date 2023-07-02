package org.xonely.view;

import org.xonely.Main;
import org.xonely.controller.LabelController;
import org.xonely.model.*;
import org.xonely.view.tools.Tools;


import java.util.*;

public class LabelView {
    private final Scanner scanner = new Scanner(System.in);
    LabelController labelController = new LabelController();
    List<Label> importedLabels;
    Tools tools = new Tools();

    Label label;
    int menuSelection;
    int selectedId;
    int lastId;

    public Post create(Post post) {
        importedLabels = labelController.getAll();

        System.out.print("Задайте имя тэга: ");
        String labelName = scanner.next();
        importedLabels = labelController.getAll();
        lastId = importedLabels.size() + 1;
        Label label = new Label(lastId, labelName);
        post.AddLabel(label);
        System.out.println(post);
        System.out.println(post.getLabels());
        labelController.add(label);
        return post;
    }

    public void showAllLabels() {
        importedLabels = labelController.getAll().stream().filter(label -> label.getStatus() != Status.DELETED).toList();
        if (!importedLabels.isEmpty()) {
            System.out.println("Список всех тэгов: ");
            importedLabels.forEach(System.out::println);
        } else {
            System.out.println("Список пуст!");
            Main.main(new String[]{}); //надеюсь так делать можно...
        }
    }

    public void deleteLabel(Post post) {
        showAllLabels();
        selectedId = tools.idSelector();
        labelController.deletebyId(selectedId);
        label = labelController.get(selectedId);
        post.updateLabel(label);
    }

    public Post showUpdateMenu(Post post) {
        showAllLabels();
        selectedId = tools.idSelector();
        label = labelController.get(selectedId);
        tools.showStatusSelection();
        menuSelection = tools.getChoice();
        labelController.statusUpdate(menuSelection, label);
        post.updateLabel(label);
        return post;

    }
}
