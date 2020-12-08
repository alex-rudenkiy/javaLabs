package ru.bstu.vt.shop;

import lombok.extern.java.Log;
import ru.bstu.vt.shop.product.Product;
import ru.bstu.vt.shop.product.milky.Kefir;
import ru.bstu.vt.shop.product.milky.Milk;
import ru.bstu.vt.shop.product.technics.Camera;
import ru.bstu.vt.shop.product.technics.Notebook;
import ru.bstu.vt.shop.product.toy.Lego;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Log
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



    static public void saveToTxtFile(String str, ArrayList<Product> store) throws IOException, IllegalAccessException, InstantiationException, InterruptedException, NoSuchMethodException, InvocationTargetException {

        try (BufferedWriter br = new BufferedWriter(new FileWriter(str))) {

            synchronized (store){
                for(Product p : store){
                    //Thread.sleep(5000+new Random().nextInt(1000 - 500 ) + 500);
                    br.write(p.toString()+"\n");
                }
                br.close();
            }

            log.info("Экспорт Store в файл успешно завершён!");
        }
    }


    static public void readFromCSVFile(String str, ArrayList<Product> store, Class<? extends Product> productClass) throws IOException, IllegalAccessException, InstantiationException, InterruptedException, NoSuchMethodException, InvocationTargetException {

        List<String> headers = new ArrayList<>(); //Массив названий колонок в CSV файле

        try (BufferedReader br = new BufferedReader(new FileReader(str))) {


            String line;
            line = br.readLine();
            headers.addAll(Arrays.asList(line.split(";")));


            while ((line = br.readLine()) != null) { //Считываем данные всех объектов в CSV файле строку за строкой ...
                String[] values = line.split(";");

                HashMap<String, String> hm = new HashMap<>();
                for (int i = 0; i < values.length; i++)
                    hm.put(headers.get(i), values[i]);

                Thread.sleep(5000+new Random().nextInt(1500 ));

                synchronized(store) {
                    store.add(productClass.getDeclaredConstructor().newInstance().init(hm)); //... и закидываем каждый новый продукт в store.
                }

                log.info(String.format("Товар %s загружен из файла (Store состоит из %d элементов).",productClass.getCanonicalName(),store.size()));

            }
        }
    }

    static public void asyncReadFromCSVFile(String str, ArrayList<Product> store, Class<? extends Product> productClass) {

        CompletableFuture.runAsync(() -> {
            try {
                readFromCSVFile(str, store, productClass);
            } catch (IOException | IllegalAccessException | InterruptedException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }
}
