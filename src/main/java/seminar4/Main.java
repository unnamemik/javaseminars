package seminar4;

import java.util.*;

public class Main {
    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";
    public static Random rand = new Random();
    static int exitFlag = 3;

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

    public static void strRecorder(int checkExit) {
        Stack<String> deck = new Stack<>();
        while (checkExit != 0) {
            System.out.print("\nВведитe текст для записи, либо команды (print, revert).\nДля выхода в основное меню нажмите 0:  ");
            Scanner sc = new Scanner(System.in);
            String inSTr = sc.next();
            checkExit = Integer.parseInt((inSTr.equals("0")) ? "0" : "3");
            if (inSTr.equals("0")) {
                break;
            } else if (inSTr.equals("print")) {
                ArrayList<String> lst = new ArrayList<>();
                for (int i = deck.size(); i > 0; i--) {
                    lst.add(deck.get(i - 1));
                }
                System.out.println(yellow + lst + reset);
            } else if (inSTr.equals("revert")) {
                deck.pop();
                System.out.println(yellow + deck + reset);
            } else {
                deck.add(inSTr);
                System.out.println(yellow + deck + reset);
            }
        }
    }

    public static LinkedList<Integer> llCreator() {
        int arrSize = userInput("\nВведите размер массива: ");
        LinkedList<Integer> lst = new LinkedList<>();
        int maxVal = userInput("Введите максимальное значение элементов массива: ");
        for (int i = 0; i < arrSize; i++) {
            lst.add(rand.nextInt(maxVal));
        }
        System.out.println("\nИсходный массив: " + lst);
        System.out.println(yellow + "---------------------------------------------" + reset);
        return lst;
    }

    public static void llReverse() {
        LinkedList<Integer> ll = llCreator();
        for (int i = 0; i < ll.size() / 2; i++) {
            int temp = ll.get(i);
            ll.set(i, ll.get(ll.size() - 1 - i));
            ll.set((ll.size() - 1 - i), temp);
        }
        System.out.println(yellow + "\nРеверсированный массив: " + ll + reset);
    }

    public static void main(String[] args) {
        while (exitFlag != 0) {
            System.out.println(cyan + """
                    \n 1. Реализовать консольное приложение, которое:
                         Принимает от пользователя и “запоминает” строки.
                         Если введено print, выводит строки так, чтобы последняя введенная была первой в списке, а первая - последней.
                         Если введено revert, удаляет предыдущую введенную строку из памяти.
                      
                     2. Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.""" + reset);
            exitFlag = userInput("\nВведите номер задачи. Для выхода нажмите 0. ");
            switch (exitFlag) {
                case 1 -> strRecorder(exitFlag);
                case 2 -> llReverse();
            }
        }
    }
}