package org.xonely.view;

import org.xonely.view.tools.Tools;

public class MainView {
    WriterView writerView = new WriterView();
    PostView postView = new PostView();
    LabelView labelView = new LabelView();
    Tools tools = new Tools();
    int menuSelection;

    public void StartApp() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Меню:");
                System.out.println("1. Добавить автора");
                System.out.println("2. Показать информацию автора по его ID");
                System.out.println("3. Изменить информацию об авторе");
                System.out.println("4. Показать все посты");
                System.out.println("5. Создать пост");
                System.out.println("6. Показать все тэги");
                System.out.println("7. Выйти");
                System.out.print("Введите номер операции: ");

                menuSelection = tools.getChoice();
                switch (menuSelection) {
                    case 1:
                        writerView.showCreateMenu();
                        break;
                    case 2:
                        writerView.getWriterInfo();
                        break;
                    case 3: //
                        writerView.showEditMenu();
                        break;
                    case 4:
                        postView.showEditMenu();
                        break;
                    case 5:
                        postView.showCreateMenu();
                        break;
                    case 6:
                        labelView.showAllLabels();
                        break;

                    case 7:
                        System.exit(0);
                    default:
                        System.out.print("Неверный выбор. Попробуйте еще раз: ");
                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Такого ID не существует.Повторите ввод.");
            }
        }
    }
}
