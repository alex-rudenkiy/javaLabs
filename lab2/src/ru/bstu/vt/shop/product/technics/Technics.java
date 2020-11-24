package ru.bstu.vt.shop.product.technics;

import lombok.ToString;
import ru.bstu.vt.shop.product.Product;

//Абстрактный класс Электро-техника
public abstract class Technics extends Product {
    @Override
    public float getCost() {
        return super.cost;
    }
    @Override
    public boolean canBuy(float cost) {
        return cost>super.cost;
    }
}
