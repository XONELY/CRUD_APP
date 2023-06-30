package org.xonely.controller;

import org.xonely.model.Writer;
import org.xonely.repository.gson.GsonWriterRepoImpl;
import org.xonely.view.PostView;

import java.util.*;


public class WriterController {
    GsonWriterRepoImpl gwri = new GsonWriterRepoImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final List<Writer> importedWriters = gwri.getAll();
    PostController postController = new PostController();
    Writer currentWriter;
    PostView postView = new PostView();
    int lastId = importedWriters.size();

    public void createWriter() {
        System.out.print("Введите Имя: ");
        String firstName = scanner.next();
        System.out.print("Введите Фамилию: ");
        String lastName = scanner.next();
        currentWriter = new Writer(lastId, firstName, lastName);
        importedWriters.add(currentWriter);
        gwri.save(currentWriter);

    }

    public void getInfo(int choice, int id) { //2
        currentWriter = importedWriters.get(id);
        switch (choice) {
            case 1:
                System.out.println(currentWriter.toString());
                break;
            case 2:
                if (currentWriter.getPosts() != null) {
                    currentWriter.getPosts().forEach(System.out::println);
                }
                break;
            case 3:
                break;
            default:
                System.err.print("Неверный выбор. Попробуйте еще раз: ");
        }
    }

    public void showAllWriters() {
        if (!importedWriters.isEmpty()) {
            System.out.println("Список всех авторов: ");
            importedWriters.forEach(w -> System.out.println(w.getFullName()));
        } else System.err.println("Список пуст!");
    }

    public void updateWriter(int choice, int id) {
        currentWriter = importedWriters.get(id);
        int postId;
        switch (choice) {
            case 1:
                System.out.println("Введите новое имя: ");
                currentWriter.setFirstName(scanner.next());
                break;
            case 2:
                System.out.println("Введите новую фамилию: ");
                currentWriter.setLastName(scanner.next());
                break;
            case 3:
                postView.showPostEditMenu();
                postId = scanner.nextInt();
                postController.EditPost(choice, currentWriter);
                break;

            case 4:
                postController.addPost(currentWriter);
                break;
            case 5:
                break;
            default:
                System.err.print("Неверный выбор. Попробуйте еще раз: ");

        }
    }
}
