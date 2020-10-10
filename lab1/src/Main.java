import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static abstract class Product {
        protected int cost;

        // Считывание параметров с консоли
        public abstract void init(Scanner scanner);

        // Возвращает стоимость товара
        public abstract int getCost();

        // Определяет, можно ли купить товар за имеющуюся сумму
        public abstract boolean canBuy(int cost);

        // Возвращает строку, представляющую указанный объект
        public abstract String toString();
    }

    //Абстрактный класс игрушка
    public static abstract class Toy extends Product {
        @Override
        public int getCost() {
            return super.cost;
        }
        @Override
        public boolean canBuy(int cost) {
            return cost>super.cost;
        }
    }

    //Класс Лего
    public static class Lego extends Toy {
        int numberOfFigures = 0;

        @Override
        public void init(Scanner scanner) {
            System.out.print("Введите цену: ");
            super.cost = scanner.nextInt();
            System.out.print("Введите кол-во фигур в наборе: ");
            numberOfFigures = scanner.nextInt();
        }

        @Override
        public String toString() {
            return String.format(
                    "{\n\tКонструктор лего (%d руб.) :\n\t\tКоличество фигур в наборе : %d\n}\n"
                    , super.cost, numberOfFigures
            );
        }
    }


    //Абстрактный класс игрушка
    public static abstract class Milky extends Product {

        @Override
        public int getCost() {
            return super.cost;
        }

        @Override
        public boolean canBuy(int cost) {
            return cost>super.cost;
        }
    }

    //Класс молоко
    public static class Milk extends Milky {
        private String whoseMilk = "";//Чьё молоко (коровье, козье)
        @Override
        public void init(Scanner scanner) {
            System.out.print("Введите цену: ");
            super.cost = scanner.nextInt();
            System.out.print("Чьё молоко (коровье, козье): ");
            whoseMilk = scanner.next();
        }

        @Override
        public String toString() {
            return String.format(
                    "{\n\tМолоко (%d руб.) :\n\t\tЧьё молоко : %s\n}\n"
                    , super.cost, whoseMilk
            );
        }
    }

    //Класс кефир
    public static class Kefir extends Milky {
        private float alcohol = 0; //Процентное содержание спирта
        private String additive = ""; //Добавка (злаки, фрукты)

        @Override
        public void init(Scanner scanner) {
            System.out.print("Введите цену: ");
            super.cost = scanner.nextInt();
            System.out.print("Процентное содержание спирта: ");
            alcohol = scanner.nextFloat();
            System.out.print("Добавка (злаки, фрукты): ");
            additive      = scanner.next();
        }

        @Override
        public String toString() {
            return String.format(
                    "{\n\tКефир (%d руб.) :\n\t\tПроцентное содержание спирта : %f\n\t\tДобавки : %s\n}\n"
                    , super.cost, alcohol, additive
            );

        }
    }


    //Абстрактный класс игрушка
    public static abstract class Technics extends Product {
        @Override
        public int getCost() {
            return super.cost;
        }
        @Override
        public boolean canBuy(int cost) {
            return cost>super.cost;
        }
    }

    //Класс камера
    public static class Camera extends Technics {
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

    //Класс ноутбук
    public static class Notebook extends Technics {
        private String os = "";
        private int ramSize = 0;
        private int hddSize = 0;

        @Override
        public void init(Scanner scanner) {
            System.out.print("Введите цену: ");
            super.cost = scanner.nextInt();
            System.out.print("Введите объём RAM (Мб.): ");
            ramSize = scanner.nextInt();
            System.out.print("Введите объём HDD (Мб.): ");
            hddSize = scanner.nextInt();
            System.out.print("Введите название предустановленной OS: ");
            os      = scanner.next();
        }

        @Override
        public String toString() {
            return String.format(
                    "{\n\tНоутбук (%d руб.) :\n\t\tОС : %s\n\t\tRAM : %d Мб.\n\t\tHDD : %d Гб.\n}\n"
                    , super.cost, os, ramSize, hddSize
            );
        }
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> store = new ArrayList<>();

        System.out.print("Введите количество товаров : ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.print("\n\nВведите тип товара : ");
            String productType = scanner.next();

            Product newProduct = null;
            switch (productType.toLowerCase()){
                case "lego"     :
                    newProduct = new Lego();
                    break;
                case "milk"     :
                    newProduct = new Milk();
                    break;
                case "kefir"    :
                    newProduct = new Kefir();
                    break;
                case "camera"   :
                    newProduct = new Camera();
                    break;
                case "notebook" :
                    newProduct = new Notebook();
                    break;
            }

            if (newProduct != null) {
                newProduct.init(scanner);
                store.add(newProduct);
            } else {
                System.out.println("\nВы не правильно ввели тип товара!");
                i--;
            }
        }

        System.out.format("\n\nСамый дорогой товар это :\n%s",store.stream().max(Comparator.comparingInt(Product::getCost)).get().toString());
    }
}