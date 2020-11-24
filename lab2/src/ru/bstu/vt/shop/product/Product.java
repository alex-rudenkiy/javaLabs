package ru.bstu.vt.shop.product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Scanner;


public abstract class Product {

    @Getter
    @Setter
    protected float cost;

    // Считывание параметров с консоли
    public abstract void init(Scanner scanner);

    // Определяет, можно ли купить товар за имеющуюся сумму
    public abstract boolean canBuy(float cost);


}
