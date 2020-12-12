package ru.bstu.vt.shop.product;

public class RequiredParameterException extends Exception
{
    public RequiredParameterException()
    {
        super("Возможно вы опечатались при наборе одного из параметров!");
    }
}
