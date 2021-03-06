package ru.bstu.vt;

import lombok.extern.java.Log;
import ru.bstu.vt.shop.Shop;
import ru.bstu.vt.shop.product.Product;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;


@Log
public class Lab4 {

    private static ThreadPoolExecutor executor  =    (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private static List<Future<?>> futures      =    new ArrayList<>();
    private static boolean checkFinish(){
        boolean allDone = true;
        for(Future<?> future : futures)
            allDone &= future.isDone();
        return allDone;
    }


    private static void loadProductsFromFolder(Scanner scanner, ArrayList<Product> store) {
        System.out.println("Введите путь к папке с данными продуктов (.csv): ");
        String dir = scanner.nextLine();
        if(!dir.equals(""))

            for (File file : Objects.requireNonNull(new File(dir).listFiles()))
                if (file.isFile())
                    try {
                        if (file.getName().contains(".csv")) {

                            Callable<Boolean> result = Shop.asyncReadFromCSVFile(
                                    file.getAbsolutePath(),
                                    store,
                                    Shop.valueOf(
                                            file.getName().replace(".csv", "").toUpperCase()
                                    ).getCode().getClass()
                            );

                            futures.add(executor.submit(result));
                        }
                    } catch (Exception ignored){}
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Product>   store   = new ArrayList<>();
        Scanner              scanner = new Scanner(System.in);
        Shop.PriceCounter priceCounter = new Shop.PriceCounter(store); //Асинхронный счётчик наиболее дорогого товара



        loadProductsFromFolder(scanner, store); //Загрузка файлов в store из csv файлов




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




        // Ручной ввод товаров в наш store
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




        while (true) {
            System.out.println("\n\nЖелаете ли вы вывести самый дорогой товар на экран? (да/нет) :");
            if (scanner.next().toLowerCase().equals("да")) {
                if(checkFinish()){
                    System.out.format("\n\nСамый дорогой товар это :\n%s",priceCounter.getMostExpensiveProduct().toString());
                }else {
                    System.out.println("Подождите, пока загрузятся все товары из файлов и попробуйте ещё раз!");
                    continue;
                }
            }
            break;
        }



        while (true) {
            System.out.println("\n\nЖелаете ли вы сохранить Store в файл? (да/нет) :");
            if (scanner.next().toLowerCase().equals("да")) {
                if(checkFinish()){
                    Shop.saveToTxtFile("out.txt", store);
                }else {
                    System.out.println("Подождите, пока загрузятся все товары из файлов и попробуйте ещё раз!");
                    continue;
                }
            }
            break;
        }
    }
}