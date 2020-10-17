package ru.shop.product.technics;

import java.util.Scanner;

//Класс камера
public class Camera extends Technics {
    private float megapixels = 0.0f;
    private boolean display = false;
    private int zoom = 0;

    @Override
    public void init(Scanner scanner) {
        System.out.print("Введите цену: ");
        super.cost = scanner.nextInt();
        System.out.print("Введите кол-во мегапикселей (напр. 7,8): ");
        megapixels  = scanner.nextFloat();
        System.out.print("Наличие дисплея (есть/нет): ");
        display     = scanner.next().toLowerCase().contains("есть");
        System.out.print("Введите степень увелечения (zoom) камеры: ");
        zoom        = scanner.nextInt();
    }

    @Override
    public String toString() {
        return String.format(
                "{\n\tКамера (%d руб.) :\n\t\tМегапиксель : %.1f мп\n\t\tЗум : %dx\n\t\tДисплей : %s\n}\n"
                , super.cost, megapixels, zoom, display ? "Есть" : "Отсутствует"
        );
    }
}
