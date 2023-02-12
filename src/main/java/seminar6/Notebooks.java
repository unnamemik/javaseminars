package seminar6;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notebooks {
    private final String reset = "\u001B[0m";
    private final String red = "\u001B[31m";
    private final String yellow = "\u001B[33m";
    private final String cyan = "\u001B[36m";
    private String model;
    private String color;
    private int display;
    private int hddSize;
    private int ramSize;
    private String osType;
    private String comment;

    public Notebooks() {
    }

    public void printFullInfo(){                                            ////////////////  Метод выводит поля экземпляров
        System.out.println(yellow+"\n1. Модель:     " + reset + model);
        System.out.println(yellow + "2. Цвет:       " + reset + color);
        System.out.println(yellow + "3. Диагональ:  " + reset + display);
        System.out.println(yellow + "4. Размер HDD: " + reset + hddSize);
        System.out.println(yellow + "5. Память RAM: " + reset + ramSize);
        System.out.println(yellow + "6. ОС:         " + reset + osType);
    }
}
