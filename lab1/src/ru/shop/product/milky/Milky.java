package ru.shop.product.milky;

import ru.shop.product.Product;

//Абстрактный класс Молочные товары
public abstract class Milky extends Product {

    @Override
    public int getCost() {
        return super.cost;
    }

    @Override
    public boolean canBuy(int cost) {
        return cost>super.cost;
    }
}

