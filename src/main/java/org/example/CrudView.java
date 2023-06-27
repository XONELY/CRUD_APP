package org.example;

public class CrudView {
    public void showIdSelectionMenu() {System.out.println("Введите ID нужного автора: ");}

    public void showMainMenu() {//0
        System.out.println();
        System.out.println("Меню:");
        System.out.println("1. Добавить автора");
        System.out.println("2. Показать информацию автора по его ID");
        System.out.println("3. Редактировать посты и информацию об авторе");
        System.out.println("4. Удалить пост");
        System.out.println("5. Показать всех авторов");
        System.out.println("6. Выйти");
        System.out.print("Введите номер операции: ");
    }
    public void createMenu() { //1
        System.out.print("Введите Имя: ");
        String firstName = CRUDController.scanner.next();
        System.out.print("Введите Фамилию: ");
        String lastName = CRUDController.scanner.next();

        WriterController.createWriter(firstName, lastName);

    }

    public void getMenuView() { //2
        System.out.println("Меню Просмотра:");
        System.out.println("1. Просмотреть всю информацию об авторе");
        System.out.println("2. Просмотреть опубликованные пост");
        System.out.println("3. Назад");
        System.out.print("Введите номер операции: ");
    }

    public void showUpdateMenu() { //3
        System.out.println("Меню Редактирования:");
        System.out.println("1. Изменить Имя");
        System.out.println("2. Изменить Фамилию");
        System.out.println("3. Изменить пост");
        System.out.println("4. Изменить статус поста");
        System.out.println("5. Создать новый пост");
        System.out.println("6. Назад");
        System.out.print("Введите номер операции: ");
    }



}
