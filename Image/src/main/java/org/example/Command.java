package org.example;


import java.io.IOException;
import java.util.Scanner;


public class Command {
    public static String help() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для ознакомление с мануалом программы введите команду: help");
        String command = scanner.nextLine();
        while (!command.equals("help")){
            System.out.println("Неверная команда, введите команду: help");
            command = scanner.nextLine();
        }
        return command;
    }
    public static String outputManual() {
        System.out.println();
        System.out.println("Мануал по использованию программы");
        System.out.println("memLocal - добавить текст к картинке с вашего компьютера");
        System.out.println("memURL - добавить текст к картинке из интернета");
        System.out.println("exit - выход из программы");
        return returnCommand();
    }

    private static String returnCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите одну из команд: memLocal, memURL, exit");
        String command = scanner.nextLine();
        while ((!command.equals("exit")) && (!command.equals("memLocal")) && (!command.equals("memURL"))){
            System.out.println("Неверная команда, введите команду:");
            command = scanner.nextLine();
        }
        return command;
    }

    public static String createMemLocal() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к изображению");
        System.out.println("Пример: C:\\Images\\cat.jpeg");
        String imagePathInput = scanner.nextLine();
        Object[] details = getDetails();
        Image.addText(Image.readImageLocal(imagePathInput), (String) details[0], (String) details[1], (String) details[2], (int) details[3], (int) details[4]);
        return returnCommand();
    }

    public static String createMemURL() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ссылку к изображению");
        System.out.println("Пример: https://gas-kvas.com/uploads/posts/2023-02/1675489758_gas-kvas-com-p-izobrazheniya-i-kartinki-na-fonovii-risuno-41.jpg");
        String imagePathInput = scanner.nextLine();
        Object[] details = getDetails();
        Image.addText(Image.readImageURL(imagePathInput), (String) details[0], (String) details[1], (String) details[2], (int) details[3], (int) details[4]);
        return returnCommand();
    }

    private static Object[] getDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке куда сохранить изображение");
        System.out.println("Пример: C:\\Images");
        String imagePathOutput = scanner.nextLine();
        System.out.println("Введите текст, который необходимо добавить к изображению:");
        String text = scanner.nextLine();
        String fontStyle = Image.getFont();
        System.out.println("Введите размер шрифта:");
        int size = scanner.nextInt();
        System.out.println("Возможные варианты расположения надписи:\n1 - текст по центру\n2 - верхний левый угол");
        System.out.println("3 - верхний правый угол\n4 - нижний левый угол\n5 - нижний правый угол:");
        System.out.println("Введите команду расположение надписи:");
        int position = scanner.nextInt();
        return new Object[] {imagePathOutput, text, fontStyle, size, position};
    }
}
