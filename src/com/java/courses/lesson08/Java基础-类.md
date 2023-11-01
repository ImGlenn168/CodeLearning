# JAVA中的类

## 一、什么是类

概念：类就是某些具备某些共同特征的实体的集合,它是一种抽象的数据类型，它是对所具有相同特征实体的抽象，在面向对象的程序设计语言中，类是对一类“事物”的属性与行为的抽象。

类可以理解为一个模板，它描述一类对象的行为和状态。

举个例子：例如一本书就可以看成一个类，那么书这个类包含了标题、标价、作者等这些属性

### **1.为什么有类这个概念**

**类的三大部件：构造器、成员变量、方法**

类是面向对象语言的程序设计中的概念，是面向对象编程的基础，类可以看成是创建Java对象的模板

类是对现实生活中一类具有共同特征的事物的抽象。如果一个程序中提供的数据类型和与应用中的概念有直接的对应，这个程序就会更容易理解，也更容易修改。

类的实质是一种引用数据类型，类似于byte、short、int(char)、long、float、double等基本的数据类型，不同的是它是一种复杂的数据类型，因为它的本质是数据类型，而不是数据，所以不存在于内存中，不能被直接操作，只有被实例化为对象时，才会变得可操作。

一组经过很好选择的用户定义的类会使程序更加简洁，此外，它还能使各种形式的代码分析更容易进行，特别地，它还会使编译器有可能检查对象的非法使用。

## 二、类的声明

### 1.类在使用前必须先声明然后才可以声明变量，创建对象

类声明语法如下：

```
[标识符] [修饰符] class 类名 {
    // 类的属性
    // 类的方法
}
```

#### 1.1标识符（访问符）

标识符：访问用于指明类、属性或者方法的访问权限，也称为访问符。

首先每个类都有访问权限，指明这些类可以被哪些类访问，JAVA在通过类声明时，指定修饰符来约定访问权限，在Java中有四种修饰符，分别是public、private、protected、default(缺省值）。

对于顶级类（外部类）来说，只有两种修饰符public和default,因为外部类的上一层单元是包，所以外部类只有两个作用域：同包，任意位置。因此只需要两种控制权限，包控制权限和公开访问权限，也就对应两种控制修饰符：public和default

对于他们所指定的访问权限如下：
**public:**

```
public: 修饰顶层类和成员类，表明该类是一个公共类，可以被任意类访问（注意：同一个java源文件只能有一个公共类）
```

**private:**

```
private:修饰成员类（内部类），表明是一个私有类，只能被顶层的类访问,顶层类就是最外层这个类

private修饰的类和方法无法被继承
```

```
class Bank {  //大括号内都属于内部
 
    //private修饰的私有类变量
    private String passWord;
}
```

```
public class PrivateTest{
    //main主函数在Bank的外部，无法取到private修饰的私有的成员变量
    public static void main(String[] args){
    Bank bank = New Bank();
    bank.cardNum = 123; //错误，无法取到私有的成员变量
 
}
}

class Bank {
 
    // private修饰的私有成员变量
    private int cardNum;
}
```

```
class Bank{
 
    private int cardNum;
 
    public classPrivateTest {
 
    public static void main (String[] args) {
    
        //main函数所在的类位于Bank类的内部，所以可以访问到Bank类下的私有成员变量
        Bank bank = New Bank();
        bank.card = 123;
}
}
}
```

**protected:**

```
protected一般不修饰类，修饰类中的成员变量，子类可以修饰父类protected修饰的变量，控制继承关系的父子之间的可见性。

protected相当于家里的银行卡，但是设置了银行卡密码，子类是儿子，可以使用家里设置了银行卡密码的银行卡

当子类和父类在同一个包中时：被声明为protected的变量能被同一个包中的任何类访问

当子类和父类不在同一个包中时：在子类中，子类实例可以访问从父类集成的protected方法，而父类实例不能访问protected方法
```

```
public class A1 {

    protected void test() {
   
}
}
```

**default(缺省）**

```
使用缺省定义的成员类或者成员变量、成员函数，只有同一个包和本类可以访问
```

## 三、类成员的声明和定义

### 1.类成员变量

**类所需要的最基本的要素：（静态）属性、（动态）方法**

**类的三大部件：构造器、成员变量、方法**

在java中，成员变量是指在类体中，类中的方法体之外定义的变量，也称为属性，如一本书作为一个类，价格、作者、封面颜色等都是书这个类的属性。

实例：

```
public class Person {
 
    //成员变量
    [修饰符] 数据类型 成员变量1;
 
    //成员方法
    public void printNumber() {
        //方法中的代码
    }
}
```

 成员变量又分为：实例变量和类变量（static静态变量）

- 类的静态成员变量只加载一次且只有一份，静态成员变量是跟类的生命周期是同步的，静态变量位于方法区，被所有类的实例共享，静态变量可以直接通过类名来访问，其生命周期取决于类的声明周期
- 实例变量取决于类的实例，每创建一个实例，java虚拟机就会为实例变量分配一次内存，实例变量位于堆区中

```
public class Person {
 
    //成员变量
    float x; //x为实例变量
    static int age;//只要有关键字static,age为类变量
 
    //成员方法
    public void printNumber() {
        //方法中的代码
    }
}
```

成员变量可被类中的方法，构造方法，及特定语句访问

声明成员变量的语法如下

```
[public] [protected] [private] [static] [final] <type> <variable_name>
```

- public、protected、private：用于表示成员变量的访问权限
- static：表示该成员变量为类变量，也成为静态变量
- final：表示将该成员变量声明为常量，其值无法更改
- type：表示变量的类型
- variable_name：表示变量的名称

在Java类的成员变量定义了类的属性，例如学生类中一般需要姓名、性别、年龄属性

定义成员变量的示例如下：

```
public class Student {
    
    //类成员变量
    public String name;  //姓名
    final int sex = 0; //性别，0表示女孩，1表示男孩
    private int age; //年龄
 
 
    //类中的方法体
    public void printNum(){
        
    }
 
}
```

上面的示例中定义了三个成员变量：

1. String类型的name，访问修饰符为public,初始化没有设置值，所以值为null;
2. int类型的sex,访问修饰符为default，初始化值为0,表示性别为女,由于被final修饰，所以无法修改；
3. int类型的age,访问修饰符为private，初始化没有设置值，所以值为null；

举例说明成员变量的初始值，代码如下

```
public class Student {
   // 静态成员变量（类变量）
   static int sum;
 
   public static void main(String[] args) {
       System.out.println(sum);
   }
}
```

- sum是被static修饰的静态变量，也称为类变量，类型为int，初始化没有赋值，所以在下面的main方法中打印输出为sum的值为0。

### 2.类成员函数（方法）

**类所需要的最基本的要素：（静态）属性、（动态）方法**

类成员函数位于类中，位于类成员变量的下面，类成员变量代表的是类的属性的话，那么类成员函数就是执行某些特定功能的方法体了，属于一种行为，例如：加速，停止等，也称为成员方法

**定义成员方法的语法如下：**

```
[修饰符] 方法的返回值数据类型 方法名（形参列表）{
                方法体语句；

                return 返回值类型相同的值；
}
```

- 修饰符：可以省略，也可以是public protected private static final abstract其中一个，其中public protected private只能出现一个
- 方法的返回值数据类型：可以是数据类型，也可以是void，如果返回值一开始不是使用的void，那么就需要在return后面跟随该类型的值或者对象
- 方法名：一般首字母小写，也适用于驼峰名命名规则，一般动词在前，名词在后，不宜过长
- 形参列表：定义方法可以接受的参数，由0-N个[数据类型，参数名]中间由[ ，]连接而成，一旦指定了形参，那么在调用该方法时就必须传入实际参数

**方法分类：**

- 无参数无返回值

  ```
  1. 在本类中调用：同一级别，直接调用，方法名（）；
  2. 在其他类中调用：对象名.方法名
  ```

- 无参数有返回值

  ```
  1. 在本类中调用：变量 = 方法名（）；（这个变量用于接收返回值）
  2. 在其他类中调用：变量 = 对象.方法名（）;（这个变量用于接收返回值）
  3. 在其他类中调用：System.out.println(对象名.方法名());
  ```

- 有参数无返回值

  ```
  1. 在本类中调用：方法名（实参列表）;
  2. 在其他类中调用：对象.方法名（实参列表）；
  ```

- 有参数有返回值

  ```
  1. 在本类中调用：变量 = 方法名（实参列表）;
  2. 在其他类中调用：变量 =  对象.方法名（实参列表）；
  3. 在其他类中调用：在其他类中调用：System.out.println(对象名.方法名(实参列表));
  ```

**变量的作用是当遇到有返回值的方法时，接收调用方法的返回值**

**如果被调用的方法的返回值类型是void,那么不要用变量接收，也不用打印方法调用，只能单独一个调用语句**

```
public class Method {
 
    public void aMethod() {
        System.out.println("无参数无返回值的方法");
    }
 
    public int bMethod() {
        System.out.println("无参数有返回值的方法");
        return 10; //return后面填与返回值类型相同的值或者对象
    }
 
    public void cMethod(int c) {
        System.out.println("有参数无返回值的方法");
    }
 
    public int dMethod(int d) {
        System.out.println("有参数有返回值的方法");
        return d; //return后面填与返回值类型相同的值或者对象
    }
 
 
    public static void main(String[] args) {
        //定义变量ret
        int ret;
        //将Method类进行实例化
        Method method = new Method();
        //通过实例化对象调用aMethod()
        method.aMethod();
        //通过实例化对象调用aMethod()
        ret = method.bMethod();
        //通过实例化对象调用cMethod()，并传入具体的实参
        method.cMethod(11);
        ret = method.dMethod(12);
 
        //打印输出ret为12,即最后一次赋值
        System.out.println(ret);
 
    }
}
```

- 方法可以没有参数，也可以有多个参数
- 方法的中定义的参数是局部变量

### 2.构造器

构造器特点：

1. 函数名必须和类名相同
2. 也称构造方法，构造函数，作用是构造出来一个类的实例，确保对象得到初始化
3. 没有返回值，连void都没有
4. 根据有无参数，可分为有参构造和无参构造
5. 使用new关键字，本质是在调用构造器
6. 一旦出现了有参构造，无参构造就必须定义
7. 构造器格式： [修饰符] 类名 （有参/无参）{ }

实例如下：

```
public class Demo {
 
    String name;
 
    //无参构造一般情况可以省略，但是如果出现了有参构造，无参构造就必须要定义
    public Demo() {
 
    }
 
    public Demo(String name) {
        this.name = name; //通过this来调用类中的属性String name中的name, = name中的name则是（String name)中的name
 
    }
}
```

在测试类的主方法中调用构造器（new的过程就是调用了构造器）

```
public class DemoApplication {
 
    public static void main(String[] args) {
 
        //new一个对象的过程就是的调用了构造器,这里调用了无参构造
        Demo demo = new Demo();
        //new一个对象的过程就是的调用了构造器,这里调用了有参构造，括号内需要传入参数
        Demo demo1 = new Demo("张");
 
        //输出结果为null
        System.out.println(demo.name);
        //输出结果为张
        System.out.println(demo1.name);
 
    }
}
```

demo.name调用的是无参构造，默认值为null,故输出为null,

demo1.name调用的是有参构造，在new的过程中传入了参数name,故打印输出的值为“张”