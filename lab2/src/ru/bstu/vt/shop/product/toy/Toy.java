package ru.bstu.vt.shop.product.toy;


import ru.bstu.vt.shop.product.Product;

//Абстрактный класс игрушка
public abstract class Toy extends Product {
    @Override
    public float getCost() {
        return super.cost;
    }
    @Override
    public boolean canBuy(float cost) {
        return cost>super.cost;
    }
}
