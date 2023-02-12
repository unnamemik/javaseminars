package seminar1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

    private static void arrMinMax() {
        int[] arr1 = arrCreator();
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int j : arr1) {
            if (j >= maxVal) {
                maxVal = j;
            }
            if (j <= minVal) {
                minVal = j;
            }
        }
        System.out.println(yellow + "Минимальное значение: " + minVal + "\nМаксимальное значение: " + maxVal + reset);
    }

    public static void leapYear() {
        int year = userInput("\nВведите год: ");
        boolean leapyear = (year > 1582 && ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)));
        if (leapyear)
            System.out.println(yellow + leapyear + reset + "\t(Год " + year + " високосный)");
        else
            System.out.println(yellow + leapyear + reset + "\t(Год " + year + " не високосный)");
    }


    public static int[] arrCreator() {
        int arrSize = userInput("\nВведите размер массива: ");
        int[] arr = new int[arrSize];

        int maxVal = userInput("Введите максимальное значение элементов массива: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (rand.nextInt(maxVal));
        }
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        System.out.println(yellow + "---------------------------------------------" + reset);
        return arr;
    }

    public static void arrSort(int[] arr) {
        int sortValue = userInput("\nВведите значение для сортировки: ");

        int leftElem, rightElem, temp;
        leftElem = 0;
        rightElem = arr.length - 1;

        while (leftElem < rightElem) {
            if (arr[leftElem] == sortValue & arr[rightElem] != sortValue) {
                temp = arr[rightElem];
                arr[rightElem] = arr[leftElem];
                arr[leftElem] = temp;
                rightElem--;
                leftElem++;

            } else if (arr[leftElem] == sortValue & arr[rightElem] == sortValue) {
                while (arr[rightElem] == sortValue & rightElem != 0) {
                    rightElem--;
                }
                temp = arr[rightElem];
                arr[rightElem] = arr[leftElem];
                arr[leftElem] = temp;
                rightElem--;
                leftElem++;

            } else if (arr[leftElem] != sortValue & arr[rightElem] == sortValue) {
                rightElem--;
                leftElem++;

            } else if (arr[leftElem] != sortValue & arr[rightElem] != sortValue) {
                leftElem++;
            }
        }
        System.out.println(yellow + "Сортированный массив: " + Arrays.toString(arr) + reset);
    }

    ////////////////////////////////////// Оптимизированный вариант кода сортировки //////////////////////////////////////////////////
//        int i, t, k;
//        k = sortValue;
//        t = 0;
//        for (i = 0; i < arr.length; i++) {
//            if (arr[i] != k) arr[t++] = arr[i];
//        }
//        for (i = t; i < arr.length; i++) {
//            arr[i] = k;
//        }
//        System.out.print(Arrays.toString(arr));
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        System.out.println(cyan + """
                1. Задать одномерный массив и найти в нем минимальный и максимальный элементы.
                                
                2. Написать метод, который определяет, является ли введенный пользователем год високосным,
                и возвращает в консоль boolean (високосный - true, не високосный - false).
                Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
                                
                3. Дан массив nums = [3,2,2,3] и число val = 3.
                Если в массиве есть числа, равные заданному, нужно перенести эти элементы в конец массива.
                Таким образом, первые несколько (или все) элементов массива должны быть отличны от заданного, а остальные - равны ему.""" + reset);
        int checkExit = 4;
        while (checkExit != 0) {
            checkExit = userInput("\nВведите номер задачи. Для выхода нажмите 0. ");
            switch (checkExit) {
                case 1 -> arrMinMax();
                case 2 -> leapYear();
                case 3 -> arrSort(arrCreator());
            }
        }

    }
}