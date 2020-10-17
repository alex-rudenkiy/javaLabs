package ru.shop.product.toy;

import ru.shop.product.Product;

//Абстрактный класс игрушка
public abstract class Toy extends Product {
    @Override
    public int getCost() {
        return super.cost;
    }
    @Override
    public boolean canBuy(int cost) {
        return cost>super.cost;
    }
}
