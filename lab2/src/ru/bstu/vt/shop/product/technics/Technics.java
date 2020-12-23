package ru.bstu.vt.shop.product.technics;

import ru.bstu.vt.shop.product.Product;

//Абстрактный класс Электро-техника
public abstract class Technics extends Product {

    //Получение стоимости товара
    @Override
    public float getCost() {
        return super.cost;
    }

    //Проверка, можно ли имея cost денег купить товар у которого стоимость super.cost
    @Override
    public boolean canBuy(float cost) {
        return cost>super.cost;
    }
}
