package org.xonely.view;

import org.xonely.controller.CRUDController;
import org.xonely.controller.WriterController;

public class WriterView {


    public void showInfo() { //2
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
        System.out.println("4. Создать новый пост");
        System.out.println("5. Назад");
        System.out.print("Введите номер операции: ");
    }
}
