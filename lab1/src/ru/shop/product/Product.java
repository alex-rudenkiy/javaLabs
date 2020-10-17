package ru.shop.product;

import java.util.Scanner;

public abstract class Product {
    protected int cost;

    // Считывание параметров с консоли
    public abstract void init(Scanner scanner);

    // Возвращает стоимость товара
    public abstract int getCost();

    // Определяет, можно ли купить товар за имеющуюся сумму
    public abstract boolean canBuy(int cost);

    // Возвращает строку, представляющую указанный объект
    public abstract String toString();

}
