package ru.shop;

import ru.shop.product.Product;
import ru.shop.product.milky.Kefir;
import ru.shop.product.milky.Milk;
import ru.shop.product.technics.Camera;
import ru.shop.product.technics.Notebook;
import ru.shop.product.toy.Lego;

import java.util.HashMap;

public class Shop{
    public static HashMap<String, Class<? extends Product>> getClasses(){
        HashMap<String, Class<? extends Product>> classes = new HashMap<>();

            classes.put(Notebook.class.getSimpleName(), Notebook.class);
            classes.put(Camera.class.getSimpleName()  , Camera.class);
            classes.put(Kefir.class.getSimpleName()   , Kefir.class);
            classes.put(Milk.class.getSimpleName()    , Milk.class);
            classes.put(Lego.class.getSimpleName()    , Lego.class);

        return classes;
    }
}