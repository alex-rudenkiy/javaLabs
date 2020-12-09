# Курс Java

 - [Лабораторная работа № 1](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab1)     (Сдано    ✅)
 - [Лабораторная работа № 2](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab2)     (Не сдано ❌)
   * [Enum](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/Shop.java);
   * [RegExp](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/regxlib/RegxLib.java)
   * Пользовательские Exception'ы [вот](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/shop/product/RequiredParameterException.java) и [вот](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/regxlib/ParseException.java)
   * Использовал коллекцию ArrayList<Product> в [main'е](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab2/src/ru/bstu/vt/Lab2.java)
 - [Лабораторная работа № 3/4/5](https://github.com/alex-rudenkiy/javaLabs/tree/main/lab3) (Не сдано ❌)
   * Добавлена многопоточность в модуле [Shop.asyncReadFromCSVFile](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab3/src/ru/bstu/vt/shop/Shop.java); 
   * Добавлены JAVA8 фичи, такие как: forEach(), Lambda Expressions, Files.walk, CompletableFuture(Concurrency API);
   * Добавлен потоковый ввод/вывод в файл через BufferedReader в функции [Shop.readFromCSVFile](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab3/src/ru/bstu/vt/shop/Shop.java) и [main](https://github.com/alex-rudenkiy/javaLabs/blob/main/lab3/src/ru/bstu/vt/Lab3.java) (для сохранения).
 
 - Курсовая работа                                                                         (Не сдано ❌)

**Формулировка задания (8 вариант):**

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
2) В цикле считывает параметры. Сначала спрашивается вид товара и создается объект нужного класса. Затем у объекта вызывается метод init() и вводятся характеристики объекта (стоимость, наименование игрушки, разрешение камеры, размер экрана ноутбука и т.д.).
Метод init() разный у разных классов.
3) Считанные объекты кладутся в массив
4) Ищется самый дорогой товар и выводится на экран (вывод через toString())

