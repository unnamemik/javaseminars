package seminar2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";

    private static int userInput(String msg) {
        Scanner sc = new Scanner(System.in);
        int inputStr;
        while (true) {
            System.out.print(msg);
            try {
                inputStr = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println(red + "Неверный ввод! Введите целое число!" + reset);
            }
        }
        return inputStr;
    }

    public static void main(String[] args) {
        System.out.println(cyan + """
                1. Напишите метод, который принимает на вход строку (String) и определяет 
                является ли строка палиндромом (возвращает boolean значение).
                                
                2. Напишите метод, который составит строку, состоящую из 100 повторений 
                слова TEST и метод, который запишет эту строку в простой текстовый файл, 
                обработайте исключения.""" + reset);
        int checkExit = 3;
        while (checkExit != 0) {
            checkExit = userInput("\nВведите номер задачи. Для выхода нажмите 0. ");
            switch (checkExit) {
                case 1 -> System.out.println(yellow + checkPalindrome() + reset);
                case 2 -> {
                    fileWriter(stringCreator());
                    //openDir();                            // Можно раскомментировать для интерактивности. )
                }
            }
        }
    }

    private static boolean checkPalindrome() {
        System.out.print("\nВведите строку для проверки. Регистр имеет значение: ");
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();        // Здесь можно использовать .toLowerCase(), например,
        // чтобы искать палиндром вне зависимости от регистра.
        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) != inputStr.charAt(inputStr.length() - (1 + i))) {
                return false;
            }
        }
        return true;
    }

    private static StringBuilder stringCreator() {
        StringBuilder testStr = new StringBuilder();
        return testStr.append("TEST ".repeat(100));
    }
    private static void fileWriter(StringBuilder testStr) {
        try (FileWriter writer = new FileWriter("Test.txt", false)) {
            writer.write(String.valueOf(testStr));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openDir() {
        try {
            String[] cmd = new String[2];
            cmd[0] = "explorer.exe";
            cmd[1] = new File("").getAbsolutePath();
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}