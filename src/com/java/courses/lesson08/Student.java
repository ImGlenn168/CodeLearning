package com.java.courses.lesson08;

public class Student {

    //类成员变量
    public String name;  //姓名
    private int sex; //性别，0表示女孩，1表示男孩
    private int age; //年龄

    public Student() {
    }

    public Student(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int age() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //类中的方法体
    public void printAge(Student student) {
        System.out.println("年纪："+age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Student student = new Student("张三",0,12);
        student.printAge(student);
        System.out.println(student);
    }
}
