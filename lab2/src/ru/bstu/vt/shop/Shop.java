package ru.bstu.vt.shop;

import ru.bstu.vt.shop.product.Product;
import ru.bstu.vt.shop.product.milky.Kefir;
import ru.bstu.vt.shop.product.milky.Milk;
import ru.bstu.vt.shop.product.technics.Camera;
import ru.bstu.vt.shop.product.technics.Notebook;
import ru.bstu.vt.shop.product.toy.Lego;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum Shop {
    NOTEBOOK(Notebook.class), CAMERA(Camera.class), KEFIR(Kefir.class), MILK(Milk.class), LEGO(Lego.class);
    private Class<? extends Product> code;

    Shop(Class<? extends Product> code) {
        this.code = code;
    }

    public Product getCode() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return code.getConstructor().newInstance();
    }

    public static String print(){
        return Arrays.stream(Shop.values()).map(e -> "* "+ e.name()).collect(Collectors.joining("\n"));
    }
}
