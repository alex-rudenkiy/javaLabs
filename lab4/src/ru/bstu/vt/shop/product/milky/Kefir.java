package ru.bstu.vt.shop.product.milky;

import lombok.*;
import lombok.extern.java.Log;
import ru.bstu.vt.regxlib.ParseException;
import ru.bstu.vt.regxlib.RegxLib;
import ru.bstu.vt.shop.product.Product;
import ru.bstu.vt.shop.product.RequiredParameterException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Класс кефир
@EqualsAndHashCode(callSuper = true)
@ToString
public class Kefir extends Milky{

    @Getter
    @Setter
    private float alcohol = 0; //Процентное содержание спирта
    @Getter
    @Setter
    private String additive; //Добавка (злаки, фрукты)

    @Override
    public void init(@NonNull Scanner scanner) {

        System.out.println("Ввод товара КЕФИР:\n\t* Пример ввода: цена = 68, спирт = 1.5, добавка = злаки *");

        while (additive==null) {
            String s = "";
            while (s.length()<1) s = scanner.nextLine();
            try {

                HashMap hm  = RegxLib.parseParametrs(s);
                super.cost  = Float.parseFloat((String) hm.get("цена"));
                alcohol     = Float.parseFloat((String) hm.get("спирт"));
                additive    = (String) hm.get("добавка");

                if(additive==null) throw new RequiredParameterException();

            } catch (NumberFormatException e){
                System.out.println("\tВы не корректно указали цену или процентное содержание спирта! Попробуйте ввести сведения о продукте заново.");
            } catch (Throwable e){
                System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
            }
        }
    }

    @Override
    public Product init(@NonNull Map hm) {
        try {

            super.cost  = Float.parseFloat((String) hm.get("цена"));
            alcohol     = Float.parseFloat((String) hm.get("спирт"));
            additive    = (String) hm.get("добавка");

            if(additive==null) throw new RequiredParameterException();

        } catch (NumberFormatException e){
            System.out.println("\tВы не корректно указали цену или процентное содержание спирта! Попробуйте ввести сведения о продукте заново.");
        } catch (Throwable e){
            System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
        }

        return this;
    }
}
