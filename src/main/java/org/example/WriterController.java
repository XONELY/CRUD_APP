package org.example;

import java.nio.file.*;
import java.util.*;


public class WriterController {

    public static int lastId = 0;
    private static final String dateNow = java.time.LocalDate.now().toString();
    private static List<WriterPOJO> writers = new ArrayList();
    public static void assignWritersJson() {
        if (Files.exists(Path.of(RepositoryController.WRITERS_FILE))) {
            writers.addAll(RepositoryController.loadWriterJson());
            WriterController.lastId = WriterController.getWriters().size()+1;


        }
    }

    public static List<PostPOJO> getPostList() {
        List<PostPOJO> posts = new ArrayList<>();
        for (WriterPOJO w : writers) {
            w.getPosts().forEach(postPOJO -> posts.add(postPOJO));
        }
        return posts;
    }

    public static List<LabelPOJO> getLabels() {
        List<LabelPOJO> labels = new ArrayList<>();
        for (PostPOJO p : getPostList()) {
            p.getLabels().forEach(labelPOJO -> labels.add(labelPOJO));
        }
        return labels;
    }


    public static void createWriter(String firstName, String lastName) {
        writers.add(new WriterPOJO(lastId+1,firstName, lastName));

    }

    public static void getInfo(int choice, int id) { //2
        var w = WriterController.getWriter(id);
        switch (choice) {
            case 1:
                System.out.println(w.toString());
                break;
            case 2:
                if (w.getPosts() != null)
                    w.getPosts().forEach(p -> System.out.println(p.toString()));
                break;
            case 3:
                break;
            default:
                System.err.println(CRUDController.wrongInput);
        }
    }

    public static List<WriterPOJO> getWriters() {
        return writers;
    }

    public static void showAllWriters() {
        if (!writers.isEmpty()) {
            System.out.println("Список всех авторов: ");
            writers.forEach(w -> System.out.println(w.toString()));
        } else System.err.println("Список пуст!");
    }


    public static WriterPOJO getWriter(int id) {
        return writers.get(id - 1);
    }

    public static void deletePost(int id) {
        System.out.println("Выберите пост: ");
        System.out.println(getWriter(id).getPosts());
        getWriter(id).getPosts().get(CRUDController.getChoice() - 1).setStatus(PostStatus.DELETED);
    }

    public static void updateWriter(int choice, int id) {
        var wr = getWriter(id);
        int postId;
        switch (choice) {
            case 1:
                System.out.println("Введите новое имя: ");
                wr.setFirstName(CRUDController.scanner.next());
                break;
            case 2:
                System.out.println("Введите новую фамилию: ");
                wr.setLastName(CRUDController.scanner.next());
                break;
            case 3:
                System.out.println("Выберите действие");
                System.out.println("1. Изменить текст поста");
                System.out.println("2. Добавить тэг для поста");
                System.out.println("3. Назад");
                choice = CRUDController.getChoice();

                switch (choice) {
                    case 1:
                        System.out.println("Выберите пост: ");
                        System.out.println(wr.getPosts());
                        postId = CRUDController.getChoice();
                        List<String> content = new ArrayList<>();
                        System.out.println("Введите текст поста. Для завершения редактирования введите 'exit':");
                        while (!(CRUDController.scanner.nextLine()).equals("exit")) {
                            content.add(CRUDController.scanner.nextLine());
                            wr.getPosts().get(postId - 1).setContent(content);
                            wr.getPosts().get(postId - 1).setUpdated(dateNow);
                        }
                        break;
                    case 2:
                        System.out.println("Выберите поста: ");
                        System.out.println(wr.getPosts());
                        postId = CRUDController.getChoice();
                        System.out.println("Введите тэг:");
                        String label = CRUDController.scanner.next();
                        wr.getPosts().get(postId - 1).AddLabel(new LabelPOJO(label));
                        break;
                    case 3:
                        break;
                    default:
                        System.err.println(CRUDController.wrongInput);
                        break;
                }
                break;
            case 4:
                PostPOJO post = wr.getPosts().get(id - 1);
                System.out.println("Выберите статус: ");
                System.err.println("1)ACTIVE ");
                System.err.println("2)DELETED ");
                System.err.println("3)UNDER_REVIEW ");
                choice = CRUDController.scanner.nextInt();

                if (choice == 1) {
                    post.setStatus(PostStatus.ACTIVE);
                } else if (choice == 2) {
                    post.setStatus(PostStatus.DELETED);
                } else {
                    post.setStatus(PostStatus.UNDER_REVIEW);
                }
                System.out.println("Статус изменен на: " + post.getStatus());

                break;

            case 5:
                System.out.print("Задайте тэг поста: ");
                String label = CRUDController.scanner.next();

                System.out.println("Введите текст поста. Для завершения редактирования введите 'exit':");

                List<String> content = new ArrayList<>();
                while (!(CRUDController.scanner.nextLine()).equals("exit")) {
                    content.add(CRUDController.scanner.nextLine());
                    wr.addPost(new PostPOJO(content, dateNow, label, PostStatus.UNDER_REVIEW));
                }
                break;
            case 6:
                break;
            default:
                System.err.println(CRUDController.wrongInput);

        }
    }
}
