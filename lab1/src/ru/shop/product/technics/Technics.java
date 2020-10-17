package ru.shop.product.technics;

import ru.bstu.lab1.Main;
import ru.shop.product.Product;

//Абстрактный класс Электро-техника
public abstract class Technics extends Product {

    @Override
    public int getCost() {
        return super.cost;
    }
    @Override
    public boolean canBuy(int cost) {
        return cost>super.cost;
    }
}
