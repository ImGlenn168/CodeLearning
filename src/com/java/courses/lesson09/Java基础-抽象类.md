# Java抽象类(基础详解)

## 1.概念

普通类是一个完善的功能类，可以直接产生实例化对象，并且在普通类中可以包含有构造方法，普通方法，static方法，常量和变量等内容。而抽象类是指在普通类的结构里面增加抽象方法的组成部分。

那么什么叫抽象方法呢？在所有的普通方法上面都会有一个"{}"，这个表示方法体，有方法体的方法一定可以被对象直接使用。而抽象方法，是指，没有方法体的方法，且抽象方法必须被abstract关键字所修饰。

拥有抽象方法体的类就被成为抽象类，抽象类必须使用abstract关键字所修饰，如果一个类包含抽象方法，所以该类必须是抽象类。注意：抽象类不一定有抽象方法，但是有抽象方法的类必须定义成抽象方法。

## 2.抽象类的使用

### 2.1抽象方法

使用abstract关键字修饰的方法就是抽象方法，值得注意的是抽象方法没有方法体，只有一个方法名。基本定义格式为：

```
修饰符 abstract 返回值类型 方法名(参数列表);
```

代码举例：

```
public abstract void func();
```

### 2.2抽象类

如果一个类包含抽象方法，那么该类必须是抽象类。

基本定义格式为：

```
abstract class 类名{
}
```

代码举例为：

```
public abstract void Test{
    public  abstract void func();
}
```

### 2.3抽象类的使用

继承抽象类的子类必须重写父类所有的抽象方法。

```
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
public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat();
    }
}
```

因此，继承抽象类的子类必须重写父类所有的抽象方法！

此时的方法重写，是子类对父类抽象方法的完成实现，我们将这种方法的重写也叫做实现方法。

2.4注意事项
关于抽象类的使用，以下为语法上要注意的细节。

1.抽象类不能创建对象，如果创建，会出现编译报错，只能创建其非抽象子类的对象(如果创建了抽象类对象，调用抽象的方法，但是抽象方法没有具体的方法体，就没有意义)；

2.抽象类种=中，可以有构造方法，是供子类创建对象时，初始化父类使用的(子类的构造方法中，有默认的supepr()，需要访问父类的构造方法)

```
abstract class Employees{
    private String name;
    private int age;
 
    public Employees(String name, int age) {
        System.out.println("抽象类的构造方法已经执行！");
        this.name = name;
        this.age = age;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
}
 
class Assit extends Employees{
    private int Assit_year;
 
    public Assit(String name, int age, int assit_year) {
        super(name, age);
        Assit_year = assit_year;
    }
 
    public int getAssit_year() {
        return Assit_year;
    }
 
    public void setAssit_year(int assit_year) {
        Assit_year = assit_year;
    }
}
public class demo2 {
    public static void main(String[] args) {
        Assit assit = new Assit("Mary",20,5);
    }
}
```

此时我们加上一个子类来继承这个抽象父类之后，子类的对象就能够被实例化。

3.抽象类中不一定包含抽象方法，但是有抽象方法的类一定是抽象类(不包含抽象方法的抽象类，目的就是不想让调用者实例化该对象，通常用于某些特殊的类的结构设计)。

4.抽象类的子类，必须重写抽象父类的所有抽象方法，否则会出现编译报错，除非该子类也是抽象类(假设不重写父类中所有的抽象方法，那么调用抽象方法会显得毫无意义) 

