package org.xonely.controller;


import org.xonely.view.*;

import java.util.Scanner;


public class CRUDController {
    CrudView crudView = new CrudView();
    WriterView writerView = new WriterView();
    WriterController writerController = new WriterController();
    PostController postController = new PostController();
    LabelController labelController = new LabelController();

    private int selectedId;
    private final Scanner scanner = new Scanner(System.in);

    public int getChoice() {
        while (!scanner.hasNextInt()) {
            System.err.print("Неверный выбор. Попробуйте еще раз: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void idSelection() {
        writerController.showAllWriters();
        System.out.println();
        System.out.println("Введите id автора");
        selectedId = getChoice();
    }

    public void StartApp() {


        while (true) {
            try {
                crudView.showMainMenu();
                int menuChoice = scanner.nextInt();
                switch (menuChoice) {
                    case 1: //create new
                        writerController.createWriter();
                        break;
                    case 2: //show info
                        idSelection();
                        writerView.showInfo();
                        menuChoice = getChoice();
                        writerController.getInfo(getChoice(), menuChoice);
                        break;
                    case 3: //
                        idSelection();
                        writerView.showUpdateMenu();
                        menuChoice = getChoice();
                        writerController.updateWriter(menuChoice, selectedId);
                        break;
                    case 4:
                        writerController.showAllWriters();
                        break;
                    case 5:
                        postController.showAllPosts();
                        break;
                    case 6:
                        labelController.showAllLabels();
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.err.print("Неверный выбор. Попробуйте еще раз: ");

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Конечно же что то сломалось...ну ничего!");
            }
        }
    }
}
