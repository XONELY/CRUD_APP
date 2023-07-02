package org.xonely.view;

import org.xonely.Main;
import org.xonely.controller.WriterController;
import org.xonely.model.*;
import org.xonely.repository.gson.GsonWriterRepoImpl;
import org.xonely.view.tools.Tools;

import java.util.List;


public class WriterView {
    private final GsonWriterRepoImpl writerRepo = new GsonWriterRepoImpl();

    WriterController writerController = new WriterController();
    PostView postView = new PostView();
    Tools tools = new Tools();
    Writer writer;
    int menuSelection;
    int selectedId;
    private List<Writer> importedWriters;

    public void showCreateMenu() {
        importedWriters = writerController.getAll();
        System.out.print("Введите Имя: ");
        String firstName = tools.getNext();
        System.out.print("Введите Фамилию: ");
        String lastName = tools.getNext();
        int lastId = importedWriters.size() + 1;
        writerController.add(new Writer(lastId, firstName, lastName));
    }

    public void getWriterInfo() {
        showAllWriters();
        selectedId = tools.idSelector();
        writer = writerController.get(selectedId);
        System.out.println(writer.toString() + "\n" +
                "Количество постов: " + writer.getPosts().size());
    }

    public void showEditMenu() {
        showAllWriters();
        selectedId = tools.idSelector();
        System.out.println("Меню Редактирования:");
        System.out.println("1. Изменить Имя");
        System.out.println("2. Изменить Фамилию");
        System.out.println("3. Присвоить пост");
        System.out.println("4. Удалить");
        System.out.println("5. Изменить статус");
        System.out.print("Введите номер операции: ");
        writer = writerController.get(selectedId);

        int choice = tools.getChoice();
        switch (choice) {
            case 1:
                System.out.println("Введите новое имя: ");
                writer.setFirstName(tools.getNext());
                writerController.updateWriter(writer);
                break;
            case 2:
                System.out.println("Введите новую фамилию: ");
                writer.setLastName(tools.getNext());
                writerController.updateWriter(writer);
                break;
            case 3:
                writer = postView.postAssign(writer);
                writerController.updateWriter(writer);
                break;
            case 4:
                writerController.deletebyId(selectedId);
                break;
            case 5:
                tools.showStatusSelection();
                menuSelection = tools.getChoice();
                writerController.statusUpdate(menuSelection, writer);
                break;
            default:
                System.err.print("Неверный выбор. Попробуйте еще раз: ");
        }
    }

    public void showAllWriters() {
        importedWriters = writerRepo.getAll().stream().filter(writer -> writer.getStatus() != Status.DELETED).toList();
        if (!importedWriters.isEmpty()) {
            System.out.println("Список всех авторов: ");
            importedWriters.forEach(System.out::println);
        } else {
            System.out.println("Список пуст!");
            Main.main(new String[]{}); //надеюсь так делать можно...
        }
    }
}
