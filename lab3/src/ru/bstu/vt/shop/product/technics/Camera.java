package ru.bstu.vt.shop.product.technics;

import lombok.*;
import ru.bstu.vt.regxlib.ParseException;
import ru.bstu.vt.regxlib.RegxLib;
import ru.bstu.vt.shop.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Класс камера
@EqualsAndHashCode(callSuper = true)
@ToString
public class Camera extends Technics {

    @Getter
    @Setter
    private float matrix = 0.0f;

    @Getter
    @Setter
    private int zoom = 0;

    @Override
    public void init(@NonNull Scanner scanner) {
        System.out.println("Ввод товара КАМЕРА:\n\t* Пример ввода: цена = 94850, матрица = 7.8, зум = 10 *");

        while (matrix==0.0f) {
            String s = "";
            while (s.length()<1) s = scanner.nextLine();
            try {

                HashMap hm  = RegxLib.parseParametrs(s);
                super.cost  = Float.parseFloat((String) hm.get("цена"));
                matrix      = Float.parseFloat((String) hm.get("матрица"));
                zoom        = Integer.parseInt((String) hm.get("зум"));

            } catch (NumberFormatException e){
                System.out.println("\tВы не корректно указали цену, матрицу или зум! Попробуйте ввести сведения о продукте заново.");
            } catch (NullPointerException e){
                System.out.println("\tВы не указали цену, матрицу или зум! Попробуйте ввести сведения о продукте заново.");
            } catch (Throwable e){
                System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
            }
        }

    }

    @Override
    public Product init(@NonNull Map hm) {
        try {
            super.cost  = Float.parseFloat((String) hm.get("цена"));
            matrix      = Float.parseFloat((String) hm.get("матрица"));
            zoom        = Integer.parseInt((String) hm.get("зум"));

        } catch (NumberFormatException e){
            System.out.println("\tВы не корректно указали цену, матрицу или зум! Попробуйте ввести сведения о продукте заново.");
        } catch (NullPointerException e){
            System.out.println("\tВы не указали цену, матрицу или зум! Попробуйте ввести сведения о продукте заново.");
        } catch (Throwable e){
            System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
        }

        return this;
    }
}
