package seminar3;

import java.util.*;

public class Main {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";
    public static Random rand = new Random();

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

    public static List<Integer> listCreator() {
        int arrSize = userInput("\nВведите размер массива: ");
        ArrayList<Integer> lst = new ArrayList<>(arrSize);
        int maxVal = userInput("Введите максимальное значение элементов массива: ");
        for (int i = 0; i < arrSize; i++) {
            lst.add(rand.nextInt(maxVal));
        }
        System.out.println("Исходный массив: " + lst);
        System.out.println(yellow + "---------------------------------------------" + reset);
        return lst;
    }

    public static List<Integer> oddOrEven(List<Integer> listInt) {
        listInt.removeIf(x -> x % 2 == 0);
        return listInt;
    }

    private static void arrMinMaxAverage() {
        List<Integer> lst1 = listCreator();
        double sum = 0;
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int elem : lst1) {
            if (elem >= maxVal) {
                maxVal = elem;
            }
            if (elem <= minVal) {
                minVal = elem;
            }
            sum += elem;
        }
        double average = sum / lst1.size();
        System.out.println(yellow + "Минимальное значение: " + minVal +
                "\nМаксимальное значение: " + maxVal +
                "\nСреднее значение: " + sum + "/" + lst1.size() + " = " + average + reset);
    }

    public static void main(String[] args) {

        int checkExit = 3;
        while (checkExit != 0) {
            System.out.println(cyan + """
                    \n1. Пусть дан произвольный список целых чисел, удалить из него четные числа (в языке уже есть что-то готовое для этого)
                    2. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее арифметическое из этого списка.""" + reset);
            checkExit = userInput("\nВведите номер задачи. Для выхода нажмите 0. ");
            switch (checkExit) {
                case 1 -> System.out.println(yellow + "Массив нечетных чисел: " + reset + oddOrEven(listCreator()));
                case 2 -> arrMinMaxAverage();
            }
        }
    }
}