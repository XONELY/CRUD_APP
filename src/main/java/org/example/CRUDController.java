package org.example;



import java.util.Scanner;


public class CRUDController {

    CrudView cv = new CrudView();

    public static final String wrongInput = "Неверный выбор. Попробуйте еще раз: ";
    public static final Scanner scanner = new Scanner(System.in);

    public static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.err.print(wrongInput);
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void StartApp() {


        while (true) {
            try {
                WriterController.assignWritersJson();
                cv.showMainMenu();
                int menuChoice = getChoice();

                switch (menuChoice) {
                    case 1:
                        cv.createMenu();
                        RepositoryController.saveToJson(RepositoryController.WRITERS_FILE);
                        break;


                    case 2:
                        cv.showIdSelectionMenu();
                        menuChoice = getChoice();
                        cv.getMenuView();
                        WriterController.getInfo(getChoice(), menuChoice);
                        break;


                    case 3:
                        cv.showIdSelectionMenu();
                        menuChoice = getChoice();
                        cv.showUpdateMenu();
                        WriterController.updateWriter(getChoice(), menuChoice);
                        RepositoryController.saveToJson(RepositoryController.POSTS_FILE);
                        RepositoryController.saveToJson(RepositoryController.LABELS_FILE);
                        RepositoryController.saveToJson(RepositoryController.WRITERS_FILE);

                        break;


                    case 4:
                        cv.showIdSelectionMenu();
                        menuChoice = getChoice();
                        WriterController.deletePost(menuChoice);
                        RepositoryController.saveToJson(RepositoryController.POSTS_FILE);
                        break;


                    case 5:
                        WriterController.showAllWriters();
                        break;


                    case 6:
                        System.exit(0);

                    default:
                        System.err.println(wrongInput);

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Конечно же что то сломалось...ну ничего!");
            }
        }
    }
}
