package org.xonely.controller;


import org.xonely.model.Status;
import org.xonely.model.Post;
import org.xonely.model.Writer;
import org.xonely.repository.gson.GsonPostRepoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostController {
    private final String dateNow = java.time.LocalDate.now().toString();
    GsonPostRepoImpl gpri = new GsonPostRepoImpl();
    private final Scanner scanner = new Scanner(System.in);
    List<Post> importedPosts = gpri.getAll();
    int lastId = importedPosts.size() - 1;
    LabelController labelController = new LabelController();


    public void addPost(Writer writer) {

        System.out.println("Введите текст поста. Для завершения редактирования введите 'exit':");
        List<String> content = new ArrayList<>();

        while (!(scanner.nextLine()).equals("exit")) {
            content.add(scanner.nextLine());
            writer.addPost(new Post(lastId, content, dateNow));

        }
    }

    public Post getPost(int postId) {
        return importedPosts.get(postId);
    }

    public void showAllPosts() {
        importedPosts.forEach(System.out::println);
    }

    public void EditPost(int choice, Writer writer) {
        int postId;
        importedPosts.forEach(System.out::println);
        System.out.println();
        System.out.println("Введите id поста");
        postId = scanner.nextInt();
        Post currentPost = importedPosts.get(postId);
        switch (choice) {
            case 1:
                List<String> content = new ArrayList<>();
                System.out.println("Введите текст поста. Для завершения редактирования введите 'exit':");
                while (!(scanner.nextLine()).equals("exit")) {
                    content.add(scanner.nextLine());
                    currentPost.updateContent(content);
                    currentPost.updateDate(dateNow);
                }
                break;
            case 2:
                labelController.addLabel(currentPost);
                break;
            case 3:
                System.out.println("Выберите статус: ");
                System.err.println("1)ACTIVE ");
                System.err.println("2)DELETED ");
                System.err.println("3)UNDER_REVIEW ");
                choice = scanner.nextInt();

                if (choice == 1) {
                    currentPost.setStatus(Status.ACTIVE);
                } else if (choice == 2) {
                    currentPost.setStatus(Status.DELETED);
                } else {
                    currentPost.setStatus(Status.UNDER_REVIEW);
                }
                System.out.println("Статус изменен на: " + currentPost.getStatus());
                break;
            default:
                System.err.print("Неверный выбор. Попробуйте еще раз: ");
                break;
        }
    }

}
