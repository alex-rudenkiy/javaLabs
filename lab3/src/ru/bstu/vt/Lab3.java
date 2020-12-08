package ru.bstu.vt;

import lombok.extern.java.Log;
import ru.bstu.vt.shop.Shop;
import ru.bstu.vt.shop.product.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


@Log
public class Lab3 {

    public static void loadProductsFromFolder(Scanner scanner, ArrayList<Product> store) throws IOException {
        System.out.println("Введите путь к папке с данными продуктов (.csv): ");
        String dir = scanner.nextLine();
        if(!dir.equals("")) {
            try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
                paths
                        .filter(Files::isRegularFile)
                        .forEach(e -> {
                            try {
                                if (e.getFileName().toString().contains(".csv"))
                                    Shop.asyncReadFromCSVFile(e.toAbsolutePath().toString(), store, Shop.valueOf(e.getFileName().toString().replace(".csv", "").toUpperCase()).getCode().getClass());
                            } catch (Exception ignored) {}
                        });
            } catch (Exception ignored){};
        }
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



        System.out.format("\n\nСамый дорогой товар это :\n%s",store.stream().max(Comparator.comparingDouble(Product::getCost)).get().toString());


        System.out.println("\n\nЖелаете ли вы сохранить Store в файл? (да/нет) :");
        if(scanner.next().toLowerCase().equals("да"))
            Shop.saveToTxtFile("out.txt", store);
    }
}