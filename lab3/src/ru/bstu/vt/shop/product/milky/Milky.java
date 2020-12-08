package ru.bstu.vt.shop.product.milky;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.bstu.vt.shop.product.Product;

//Абстрактный класс Молочные товары
public abstract class Milky extends Product {

    @Override
    public float getCost() {
        return super.cost;
    }

    @Override
    public boolean canBuy(float cost) {
        return cost>super.cost;
    }
}

