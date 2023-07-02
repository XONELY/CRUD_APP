package org.xonely.view.tools;

import java.util.Scanner;

public class Tools {
    private final String dateNow = java.time.LocalDate.now().toString();
    private final Scanner scanner = new Scanner(System.in);

    public String getDateNow(){
        return dateNow;
    }
    public int getChoice() {
        while (!scanner.hasNextInt()) {
            System.err.print("Неверный выбор. Попробуйте еще раз: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    public int idSelector() {
        System.out.println();
        System.out.print("Введите id: ");
        return getChoice()-1;
    }

    public void showStatusSelection(){
        System.out.println("Выберите статус: ");
        System.err.println("1)ACTIVE ");
        System.err.println("2)DELETED ");
        System.err.println("3)UNDER_REVIEW ");
    }

    public String getNext(){
       return scanner.next();
    }
    public String getNextLine(){
        return scanner.nextLine();
    }
}
