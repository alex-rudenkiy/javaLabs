package ru.bstu.vt.shop.product;

import lombok.*;
import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;


public abstract class Product {

    @Getter
    @Setter
    protected float cost;

    // Считывание параметров с консоли
    public abstract void init(Scanner scanner);

    public abstract Product init(@NonNull Map hm);


    // Определяет, можно ли купить товар за имеющуюся сумму
    public abstract boolean canBuy(float cost);


}
