package ru.bstu.lab1;

import ru.shop.Shop;
import ru.shop.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> store = new ArrayList<>();
        HashMap<String, Class> classes = Shop.getClasses();

        System.out.print("Введите количество товаров : ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("\n\nВведите тип товара : ");
            String productType = scanner.next();

            try {
                Product newProduct = ((Class<Product>) classes.get(productType)).newInstance();
                newProduct.init(scanner);
                store.add(newProduct);
            } catch (Throwable e) {
                System.out.println("\nВы не правильно ввели тип товара!");
                i--;
            }
        }

        System.out.format("\n\nСамый дорогой товар это :\n%s",store.stream().max(Comparator.comparingInt(Product::getCost)).get().toString());
    }
}