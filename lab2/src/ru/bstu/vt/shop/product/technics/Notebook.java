package ru.bstu.vt.shop.product.technics;

import lombok.*;
import ru.bstu.vt.regxlib.ParseException;
import ru.bstu.vt.regxlib.RegxLib;
import ru.bstu.vt.shop.product.RequiredParameterException;

import java.util.HashMap;
import java.util.Scanner;

//Класс ноутбук
@EqualsAndHashCode(callSuper = true)
@ToString
public class Notebook extends Technics {

    @Getter
    @Setter
    private String os;
    @Getter
    @Setter
    private int ramSize = 0;
    @Getter
    @Setter
    private int hddSize = 0;

    @Override
    public void init(@NonNull Scanner scanner) {
        System.out.println("Ввод товара НОУТБУК:\n\t* Пример ввода: цена = 134500, ram = 64, hdd = 1000, os = linux *");

        while (ramSize==0) {
            String s = "";
            while (s.length()<1) s = scanner.nextLine();
            try {

                HashMap hm  = RegxLib.parseParametrs(s);
                super.cost  = Float.parseFloat((String) hm.get("цена"));
                ramSize     = Integer.parseInt((String) hm.get("ram"));
                hddSize     = Integer.parseInt((String) hm.get("hdd"));
                os          = (String) hm.get("os");

                if(os==null) throw new RequiredParameterException();

            } catch (NumberFormatException e){
                System.out.println("\tВы не корректно указали цену, ram или hdd! Попробуйте ввести сведения о продукте заново.");
            } catch (Throwable e){
                System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
            }
        }
    }
}
