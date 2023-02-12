package seminar6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static seminar6.draft.*;

public class Main {

    static String filename = "E:\\git\\java\\javaseminars\\src\\main\\java\\seminar6\\db.csv";
    static int itemQuant;
    static String property;
    static ArrayList<Notebooks> items = new ArrayList<>(itemQuant);

    private static int userIntegerInput(String msg) {
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

    private static String userStringInput(String msg) {
        Scanner sc = new Scanner(System.in);
        String inputStr;
        while (true) {
            System.out.print(msg);
            try {
                inputStr = sc.next();
                break;
            } catch (NumberFormatException e) {
                System.out.println(red + "Неверный ввод!" + reset);
            }
        }
        return inputStr;
    }

    public static void dbReader(String filename) throws IOException {       ////////// Метод читает базу ноутов из csv
        BufferedReader fp = new BufferedReader(new FileReader(filename));
        while (fp.readLine() != null) {
            itemQuant++;
        }

        fp = new BufferedReader(new FileReader(filename));
        for (int i = 0; i < itemQuant; i++) {
            items.add(new Notebooks());
        }

        String[] currStr;
        while (fp.ready()) {
            for (int i = 0; i < itemQuant; i++) {
                currStr = fp.readLine().split(",");
                items.get(i).setModel(currStr[0]);
                items.get(i).setColor(currStr[1]);
                items.get(i).setDisplay(Integer.parseInt(currStr[2]));
                items.get(i).setHddSize(Integer.parseInt(currStr[3]));
                items.get(i).setRamSize(Integer.parseInt(currStr[4]));
                items.get(i).setOsType(currStr[5]);
            }
        }
    }

    public static void itemSelect() {                   //////////////////       Метод выбора по параметрам
        System.out.println(yellow + """
                \n1. Модель
                2. Цвет
                3. Диагональ экрана
                4. Объем жёсткого диска
                5. Размер оперативной памяти
                6. Операционная система""" + reset);

        int checkExit = 7;
        while (checkExit != 0) {
            checkExit = userIntegerInput("\nВведите номер параметра для поиска. Для выхода в предыдущее меню нажмите 0. ");
            switch (checkExit) {
                case 1 -> {
                    property = userStringInput("\nВведите значение параметра: ");
                    for (int i = 0; i < itemQuant; i++) {
                        if (Objects.equals(items.get(i).getModel(), property)) {
                            items.get(i).printFullInfo();
                        }
                    }
                }
                case 2 -> {
                    property = userStringInput("\nВведите значение параметра: ");
                    for (int i = 0; i < itemQuant; i++) {
                        if (Objects.equals(items.get(i).getColor(), property)) {
                            items.get(i).printFullInfo();
                        }
                    }
                }
                case 3 -> {
                    property = String.valueOf(userIntegerInput("\nВведите значение параметра: "));
                    for (int i = 0; i < itemQuant; i++) {
                        if (items.get(i).getDisplay() == Integer.parseInt(property)) {
                            items.get(i).printFullInfo();
                        }
                    }
                }
                case 4 -> {
                    property = String.valueOf(userIntegerInput("\nВведите значение параметра: "));
                    for (int i = 0; i < itemQuant; i++) {
                        if (items.get(i).getHddSize() == Integer.parseInt(property)) {
                            items.get(i).printFullInfo();
                        }
                    }
                }
                case 5 -> {
                    property = String.valueOf(userIntegerInput("\nВведите значение параметра: "));
                    for (int i = 0; i < itemQuant; i++) {
                        if (items.get(i).getRamSize() == Integer.parseInt(property)) {
                            items.get(i).printFullInfo();
                        }
                    }
                }
                case 6 -> {
                    property = userStringInput("\nВведите значение параметра: ");
                    for (int i = 0; i < itemQuant; i++) {
                        if (Objects.equals(items.get(i).getOsType(), property)) {
                            items.get(i).printFullInfo();
                        }
                    }
                }
            }
            System.out.println(yellow + "\nПоиск завершен." + reset);
        }
    }

        public static void getAllItems () {                         /////////////        Выводит все ноутбуки из базы
            for (int i = 0; i < itemQuant; i++) {
                items.get(i).printFullInfo();
            }
        }


        public static void main (String[]args) throws IOException {
            dbReader(filename);

            int checkExit = 3;
            while (checkExit != 0) {
                System.out.println(cyan + """
                    \n1. Найти по параметру:                                    
                    2. Вывести все ноутбуки:""" + reset);
                checkExit = userIntegerInput("\nВведите номер действия. Для выхода нажмите 0. ");
                switch (checkExit) {
                    case 1 -> itemSelect();
                    case 2 -> getAllItems();
                }
            }
        }

}