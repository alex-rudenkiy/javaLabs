package ru.shop.product.toy;

import java.util.Scanner;

//Класс Лего
public class Lego extends Toy {
    int numberOfFigures = 0;

    @Override
    public void init(Scanner scanner) {
        System.out.print("Введите цену: ");
        super.cost = scanner.nextInt();
        System.out.print("Введите кол-во фигур в наборе: ");
        numberOfFigures = scanner.nextInt();
    }

    @Override
    public String toString() {
        return String.format(
                "{\n\tКонструктор лего (%d руб.) :\n\t\tКоличество фигур в наборе : %d\n}\n"
                , super.cost, numberOfFigures
        );
    }
}
