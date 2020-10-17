package ru.shop.product.technics;

import java.util.Scanner;

//Класс ноутбук
public class Notebook extends Technics {
    private String os = "";
    private int ramSize = 0;
    private int hddSize = 0;

    @Override
    public void init(Scanner scanner) {
        System.out.print("Введите цену: ");
        super.cost = scanner.nextInt();
        System.out.print("Введите объём RAM (Мб.): ");
        ramSize = scanner.nextInt();
        System.out.print("Введите объём HDD (Мб.): ");
        hddSize = scanner.nextInt();
        System.out.print("Введите название предустановленной OS: ");
        os      = scanner.next();
    }

    @Override
    public String toString() {
        return String.format(
                "{\n\tНоутбук (%d руб.) :\n\t\tОС : %s\n\t\tRAM : %d Мб.\n\t\tHDD : %d Гб.\n}\n"
                , super.cost, os, ramSize, hddSize
        );
    }
}
