package seminar5;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String cyan = "\u001B[36m";

    private static class CurPoint {
        public CurPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int x;
        public int y;
    }

    static int width = 11;
    static int height = 11;
    static char border = '▓';
    static char[][] map;
    static List<CurPoint> wave = new ArrayList<>();

    public static void WaveAlgorithm(int width, int height) {
        map = new char[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = ' ';
            }
        }
        for (int i = 0; i < width; i++) {
            map[i][0] = border;
            map[width - 1][i] = border;
        }
        for (int i = 0; i < height; i++) {
            map[0][i] = border;
            map[i][height - 1] = border;
        }
    }

    public static void findPath(int x, int y, int next_x, int next_y) {
        if (map[y][x] == border || map[next_y][next_x] == border) {
            System.out.println(red + "Положение указано неверно!" + reset);
            return;
        }

        char[][] cloneMap = clone(map);
        List<CurPoint> oldWave = new ArrayList<>();
        oldWave.add(new CurPoint(next_x, next_y));
        int next_step = 0;
        map[next_y][next_x] = (char) next_step;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        while (oldWave.size() > 0) {
            next_step++;
            wave.clear();
            for (CurPoint i : oldWave) {
                for (int d = 0; d < 4; d++) {
                    next_x = i.x + dx[d];
                    next_y = i.y + dy[d];

                    if (map[next_y][next_x] == ' ') {
                        wave.add(new CurPoint(next_x, next_y));
                        map[next_y][next_x] = (char) next_step;
                    }
                }
            }
            oldWave = new ArrayList<>(wave);
        }

        boolean flag;
        wave.clear();
        wave.add(new CurPoint(x, y));
        while (map[y][x] != 0) {
            flag = true;
            for (int d = 0; d < 4; d++) {
                next_x = x + dx[d];
                next_y = y + dy[d];
                if (map[y][x] - 1 == map[next_y][next_x]) {
                    x = next_x;
                    y = next_y;
                    wave.add(new CurPoint(x, y));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(red + "Пути нет!" + reset);
                break;
            }
        }

        map = cloneMap;
        for (CurPoint i : wave) {
            map[i.y][i.x] = '□';
        }
    }

    public static void traceOut() {
        StringBuilder m;
        System.out.print(" \\ ");
        for (int i = 0; i < height; i++) {
            System.out.print(i > 9 ? cyan + i + "" + reset: cyan + i + " " + reset);
        }
        System.out.println();
        for (int i = 0; i < width; i++) {
            m = new StringBuilder(i > 9 ? cyan + i + " " + reset : cyan + i + "  " + reset);
            for (int j = 0; j < height; j++) {
                m.append(map[i][j] > 9 ? map[i][j] + " " : map[i][j] + "  ");
            }
            System.out.println(m);
        }
    }

    private static char[][] clone(char[][] map) {
        char[][] cloneMap = new char[width][height];
        for (int i = 0; i < map.length; i++)
            System.arraycopy(map[i], 0, cloneMap[i], 0, map[i].length);
        return cloneMap;
    }

    public static void main(String[] args) {

        int x = 0;
        int y = 0;
        int next_x = 0;
        int next_y = 0;

        int[][] s_map = new int[][]{
                {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
                {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
                {00, 00, -1, 00, -1, 00, 00, 00, -1, 00, 00},
                {00, 00, -1, 00, 00, 00, 00, 00, -1, 00, 00},
                {00, 00, -1, 00, 00, 00, 00, 00, -1, 00, 00},
                {00, 00, -1, -1, -1, -1, -1, -1, -1, 00, 00},
                {00, 00, -1, 00, 00, 00, 00, 00, -1, 00, 00},
                {00, 00, -1, 00, 00, 00, 00, 00, -1, 00, 00},
                {00, 00, 00, 00, 00, 00, 00, 00, -1, 00, 00},
                {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00},
                {00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00}
        };


        WaveAlgorithm(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (s_map[i][j] == -1) {
                    x = j;
                    y = i;
                    map[y][x] = border;
                }
            }
        }

        traceOut();
        System.out.println();

        Scanner scan = new Scanner(System.in);
        System.out.println(yellow + "Поиск кратчайшего пути волновым алгоритмом." + reset);
        try {
            System.out.println("Введите x начала");
            x = scan.nextInt();
            System.out.println("Введите y начала");
            y = scan.nextInt();
            System.out.println("Введите x конца");
            next_x = scan.nextInt();
            System.out.println("Введите y конца");
            next_y = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(red + "Неверный ввод!" + reset);
        }

        findPath(x, y, next_x, next_y);
        traceOut();
        System.out.println();
    }
}