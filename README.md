# Курс Java ☕

 - [Лабораторная работа № 1](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab1)     (Сдано    ✅)
 - [Лабораторная работа № 2](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab2)     (Не сдано ❌)
   * Добавил Maven, см. [pom.xml](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/pom.xml);
   * Переписал с использованием [Enum](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/Shop.java);
   * Добавил Lombok: [@EqualsAndHashCode](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/technics/Camera.java), [@ToString](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/technics/Camera.java), [@Getter](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/technics/Camera.java), [@Setter](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/technics/Camera.java), [@NonNull](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/technics/Camera.java), [@Log](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab3/src/ru/bstu/vt/shop/Shop.java);
   * Добавил [RegExp](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/regxlib/RegxLib.java);
   * Добавил пользовательские Exception'ы [вот](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/RequiredParameterException.java) и [вот](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/regxlib/ParseException.java);
   * Переделал на коллекцию `ArrayList<Product>` в [main'е](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/Lab2.java).
  - [Лабораторная работа № 3](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab3)     (Не сдано ❌)
   * Добавлен потоковый ввод/вывод в файл через `BufferedReader` и `BufferedWriter`, см. функцию [Shop.readFromCSVFile](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab3/src/ru/bstu/vt/shop/Shop.java) (для чтения) и [Shop.saveToTxtFile](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab3/src/ru/bstu/vt/Lab3.java) (для сохранения).
  - [Лабораторная работа № 4](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab4)     (Не сдано ❌)
   * Добавлена многопоточность, см. [Shop.asyncReadFromCSVFile](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab4/src/ru/bstu/vt/shop/Shop.java); 
  - [Лабораторная работа № 5](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab5) (Не сдано ❌)
   * Добавлены JAVA8 фичи, такие как: `forEach()`, Lambda Expressions, `Files.walk`, `CompletableFuture`(Concurrency API);
 - Курсовая работа (Не сдано ❌) ... в процессе 🏗️

**📄 Формулировка задания (8 вариант):**

> Создать абстрактный класс Product с методами:

    public abstract void init(Scanner scanner) // считывание параметров с консоли
    public abstract int getCost() // возвращает стоимость товара
    public abstract boolean canBuy(int cost); // определяет, можно ли купить товар за имеющуюся сумму
    public String toString() // возвращается состояние объекта в виде строки (определяется только в наследниках, т.к. определен в Object)

>Построить иерархию классов:

|  | | |
|--|--|--|
| Товар | → Игрушка  | → Лего |
|  | → Молочный | → Молоко |
|  | | → Кефир|
|  | → Техника | → Камера|
|  | | → Ноутбук|

> Написать программу, которая:
1) Считывает с консоли количество товаров.
2) В цикле считывает параметры. Сначала спрашивается вид товара и создается объект нужного класса. Затем у объекта вызывается метод `init()` и вводятся характеристики объекта (стоимость, наименование игрушки, разрешение камеры, размер экрана ноутбука и т.д.).
Метод `init()` разный у разных классов.
3) Считанные объекты кладутся в массив
4) Ищется самый дорогой товар и выводится на экран (вывод через `toString()`)

