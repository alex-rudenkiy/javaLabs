package ru.shop.product.milky;

import java.util.Scanner;

//Класс кефир
public class Kefir extends Milky{
    private float alcohol = 0; //Процентное содержание спирта
    private String additive = ""; //Добавка (злаки, фрукты)

    @Override
    public void init(Scanner scanner) {
        System.out.print("Введите цену: ");
        super.cost = scanner.nextInt();
        System.out.print("Процентное содержание спирта: ");
        alcohol = scanner.nextFloat();
        System.out.print("Добавка (злаки, фрукты): ");
        additive      = scanner.next();
    }

    @Override
    public String toString() {
        return String.format(
                "{\n\tКефир (%d руб.) :\n\t\tПроцентное содержание спирта : %f\n\t\tДобавки : %s\n}\n"
                , super.cost, alcohol, additive
        );

    }
}
