package ru.bstu.vt;

import lombok.extern.java.Log;
import ru.bstu.vt.shop.Shop;
import ru.bstu.vt.shop.product.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;


@Log
public class Lab3 {

    private static void loadProductsFromFolder(Scanner scanner, ArrayList<Product> store) {
        System.out.println("Введите ПУТЬ к папке с данными продуктов (.csv): ");
        String dir = scanner.nextLine();
        if(!dir.equals(""))
            for (File file : Objects.requireNonNull(new File(dir).listFiles()))
                if (file.isFile())
                    try {
                        if (file.getName().contains(".csv"))
                            Shop.readFromCSVFile(file.getAbsolutePath(), store, Shop.valueOf(file.getName().replace(".csv", "").toUpperCase()).getCode().getClass());
                    } catch (Exception ignored) {}

    }

    public static void main(String[] args) throws IOException {
        ArrayList<Product>   store   = new ArrayList<>();
        Scanner              scanner = new Scanner(System.in);


        loadProductsFromFolder(scanner, store);


        log.info("StoreManager started");
        System.out.println(
                "\n---- Список возможных товаров ---- \n"+
                Shop.print()+
                "\n----------------------------------\n"
        );





        int n = -1;
        System.out.print("Введите количество товаров : ");

        while (n==-1)  //Цикл который обеспечивает ввод N до тех пор, пока не будет введено число
            try {
                n = Integer.parseInt(scanner.next());
            }catch(Exception e){
                System.out.println("Вам необходимо ввести целое число, попробуйте заново!");
            }





        for (int i = 0; i < n; i++) {

            System.out.print("\nВведите тип товара (либо @@@SAVE - для экспорта store в файл) : ");
            String productType = scanner.next();

            if(productType.equals("@@@SAVE")) {
                Shop.saveToTxtFile("out.txt", store);
            } else {

                try {

                    Product newProduct = Shop.valueOf(productType.toUpperCase()).getCode();
                    newProduct.init(scanner);
                    store.add(newProduct);

                } catch (Throwable e) {
                    log.warning("Не правильно был введён тип товара!");
                    i--;
                }

            }
        }



        store.sort(Comparator.comparingDouble(Product::getCost));
        System.out.format("\n\nСамый дорогой товар (%.2f руб.) это :\n%s", store.get(store.size()-1).getCost(), store.get(store.size()-1));


        System.out.println("\n\nЖелаете ли вы сохранить Store в файл? (да/нет) :");
        if(scanner.next().toLowerCase().equals("да"))
            Shop.saveToTxtFile("out.txt", store);
    }
}