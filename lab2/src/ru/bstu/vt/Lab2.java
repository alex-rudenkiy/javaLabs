package ru.bstu.vt;

import lombok.extern.java.Log;
import ru.bstu.vt.shop.Shop;
import ru.bstu.vt.shop.product.Product;
import ru.bstu.vt.shop.product.milky.Kefir;
import ru.bstu.vt.shop.product.toy.Lego;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public class Lab2 {
    private static ArrayList<Product>   store   = new ArrayList<>();
    private static Scanner              scanner = new Scanner(System.in);


    public static void main(String[] args) {

        log.info("StoreManager started");
        System.out.println(
                "\n---- Список возможных товаров ---- \n"+
                Shop.print()+
                "\n----------------------------------\n"
        );





        int n = -1;
        System.out.print("Введите количество товаров : ");

        while (n==-1) //Цикл который обеспечивает ввод N до тех пор, пока не будет введено число
        try {
            n = Integer.parseInt(scanner.next());
        }catch(Exception e){
            System.out.println("Вам необходимо ввести целое число, попробуйте заново!");
        }





        for (int i = 0; i < n; i++) {

            System.out.print("\nВведите тип товара : ");
            String productType = scanner.next();

            try {

                Product newProduct = Shop.valueOf(productType.toUpperCase()).getCode();
                newProduct.init(scanner);
                store.add(newProduct);

            } catch (Throwable e) {
                log.warning("Не правильно был введён тип товара!");
                i--;
            }
        }



        System.out.format("\n\nСамый дорогой товар это :\n%s",store.stream().max(Comparator.comparingDouble(Product::getCost)).get().toString());
    }
}