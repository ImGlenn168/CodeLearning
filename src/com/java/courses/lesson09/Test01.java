package com.java.courses.lesson09;

abstract class Animal{
    String name = "猫咪";
    abstract void eat();
}

class Cat extends Animal{

    @Override
    void eat() {
        System.out.println(name + "正在吃饭");
    }
}
public class Test01 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat();
    }
}