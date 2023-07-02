package org.xonely.view;

import org.xonely.Main;
import org.xonely.controller.PostController;
import org.xonely.model.*;
import org.xonely.repository.gson.GsonPostRepoImpl;
import org.xonely.view.tools.Tools;

import java.util.ArrayList;
import java.util.List;

public class PostView {


    GsonPostRepoImpl gpri = new GsonPostRepoImpl();
    private List<Post> importedPosts;
    PostController postController = new PostController();
    LabelView labelView = new LabelView();
    Tools tools = new Tools();

    int menuSelection;
    int selectedId;
    Post post;

    public void showCreateMenu() {
        importedPosts = gpri.getAll();
        System.out.println("Введите текст поста. Для завершения редактирования введите 'exit':");
        List<String> content = new ArrayList<>();
        while (true) {
            String line = tools.getNextLine();
            if (line.equals("exit")) {
                break;
            }
            content.add(line);
        }
        int lastId = importedPosts.size() + 1;
        post = new Post(lastId, content.stream().distinct().toList(), tools.getDateNow());
        postController.add(post);
    }


    public void showEditMenu() {
        showAllPosts();
        if (!importedPosts.isEmpty()) {
            System.out.println("Начать редактировать?");
            System.out.println("1)Да");
            System.out.println("2)Нет");
            menuSelection = tools.getChoice();
            if (menuSelection == 1) {
                importedPosts = postController.getAll();
                selectedId = tools.idSelector();
                post = importedPosts.get(selectedId);

                System.out.println();
                System.out.println("Выберите действие");
                System.out.println("1. Изменить текст поста");
                System.out.println("2. Добавить тэг для поста");
                System.out.println("3. Изменить статус поста");
                System.out.println("4. Изменить статус тэга");
                System.out.println("5. Удалить пост");
                System.out.println("6. Удалить тэг");
                System.out.println("7. Посмотреть тэги поста");
                System.out.println("8. Назад");

                menuSelection = tools.getChoice();
                System.out.println();

                switch (menuSelection) {
                    case 1:
                        List<String> content = new ArrayList<>();
                        System.out.println("Введите текст поста. Для завершения редактирования введите 'exit':");
                        while (!(tools.getNextLine()).equals("exit")) {
                            content.add(tools.getNextLine());
                            postController.updatePost(post, content, tools.getDateNow());
                        }
                        break;
                    case 2:
                        System.out.println(post);/////
                        post = labelView.create(post);
                        postController.updatePost(post);
                        break;
                    case 3:
                        tools.showStatusSelection();
                        menuSelection = tools.getChoice();
                        postController.statusUpdate(menuSelection, post);
                        System.out.println("Статус изменен на: " + post.getStatus());
                        break;
                    case 4:
                        post = labelView.showUpdateMenu(post);
                        postController.updatePost(post);
                        break;
                    case 5:
                        postController.deletebyId(selectedId);
                        break;
                    case 6:
                        labelView.deleteLabel(post);
                        postController.updatePost(post);
                        break;
                    case 7:
                        System.out.println(post.getLabels());
                        break;
                    case 8:
                        break;
                    default:
                        System.out.print("Неверный выбор. Попробуйте еще раз: ");
                        break;
                }
            }
        }
    }


    public Writer postAssign(Writer writer) {
        showAllPosts();
        selectedId = tools.idSelector();
        post = postController.get(selectedId);
        writer.addPost(post);
        return writer;
    }

    public void showAllPosts() {
        importedPosts = postController.getAll().stream().filter(post -> post.getStatus() != Status.DELETED).toList();
        if (!importedPosts.isEmpty()) {
            System.out.println("Список всех постов: ");
            importedPosts.forEach(System.out::println);
        } else {
            System.err.println("Список пуст!");
            Main.main(new String[]{}); //надеюсь так делать можно...
        }
    }
}
