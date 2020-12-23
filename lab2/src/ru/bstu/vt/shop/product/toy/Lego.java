package ru.bstu.vt.shop.product.toy;

import lombok.*;
import ru.bstu.vt.regxlib.ParseException;
import ru.bstu.vt.regxlib.RegxLib;

import java.util.HashMap;
import java.util.Scanner;

//Класс Лего
@EqualsAndHashCode(callSuper = true)
@ToString
public class Lego extends Toy {

    @Getter
    @Setter
    private int numberOfFigures = -1;

    @Override
    public void init(@NonNull Scanner scanner) {
        System.out.println("Ввод товара ЛЕГО:\n\t* Пример ввода: цена = 2400, фигур = 240 *");

        while (numberOfFigures==-1) {
            String s = "";
            while (s.length()<1) s = scanner.nextLine();
            try {

                HashMap<String, String> hm      = RegxLib.parseParametrs(s);
                super.cost      = Float.parseFloat(hm.get("цена"));
                numberOfFigures = Integer.parseInt(hm.get("фигур"));

            } catch (NumberFormatException e){
                System.out.println("\tВы не корректно указали цену или кол-во фигур! Попробуйте ввести сведения о продукте заново.");
            } catch (Throwable e){
                System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
            }
        }
    }
}
