package org.xonely.view;

import org.xonely.controller.CRUDController;
import org.xonely.controller.WriterController;

public class CrudView {

    public void showMainMenu() {//0
        System.out.println();
        System.out.println("Меню:");
        System.out.println("1. Добавить автора"); //wcontroller create
        System.out.println("2. Показать информацию автора по его ID"); //wview // showInfo
        System.out.println("3. Редактировать посты и информацию об авторе"); //wview // showUpdate
        System.out.println("4. Показать всех авторов");//wcontroller showall
        System.out.println("5. Показать все посты"); //crudcontroller exit
        System.out.println("6. Показать все тэги");
        System.out.println("7. Выйти");
        System.out.print("Введите номер операции: ");
    }




}
