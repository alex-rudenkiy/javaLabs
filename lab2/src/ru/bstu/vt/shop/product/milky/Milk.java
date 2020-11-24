package ru.bstu.vt.shop.product.milky;

import lombok.*;
import ru.bstu.vt.regxlib.ParseException;
import ru.bstu.vt.regxlib.RegxLib;
import ru.bstu.vt.shop.product.RequiredParameterException;

import java.util.HashMap;
import java.util.Scanner;

//Класс молоко
@EqualsAndHashCode(callSuper = true)
@ToString
public class Milk extends Milky{

    @Getter
    @Setter
    private String whoseMilk;//Чьё молоко (коровье, козье)

    @Override
    public void init(@NonNull Scanner scanner) {
        System.out.println("Ввод товара МОЛОКО:\n\t* Пример ввода: цена = 123, молоко = коровье *");

        while (whoseMilk==null) {
            String s = "";
            while (s.length()<1) s = scanner.nextLine();
            try {
                HashMap hm = RegxLib.parseParametrs(s);
                super.cost = Float.parseFloat((String) hm.get("цена"));
                whoseMilk = (String) hm.get("молоко");

                if(whoseMilk==null) throw new RequiredParameterException();

            } catch (NumberFormatException e){
                System.out.println("\tВы не корректно указали цену! Попробуйте ввести сведения о продукте заново.");
            } catch (Throwable e){
                System.out.println("\t"+e.getMessage()+" Попробуйте ввести сведения о продукте заново.");
            }
        }
    }
}
