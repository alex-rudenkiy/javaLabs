package ru.shop.product.milky;

import java.util.Scanner;

//Класс молоко
public class Milk extends Milky{
    private String whoseMilk = "";//Чьё молоко (коровье, козье)

    @Override
    public void init(Scanner scanner) {
        System.out.print("Введите цену: ");
        super.cost = scanner.nextInt();
        System.out.print("Чьё молоко (коровье, козье): ");
        whoseMilk = scanner.next();
    }

    @Override
    public String toString() {
        return String.format(
                "{\n\tМолоко (%d руб.) :\n\t\tЧьё молоко : %s\n}\n"
                , super.cost, whoseMilk
        );
    }
}
