# Java枚举详解

#### **枚举的场景及历史：**

枚举是一个被命名的整型常数的集合，用于声明一组带标识符的常数。枚举在曰常生活中很常见，例如一个人的性别只能是“男”或者“女”，一周的星期只能是 7 天中的一个等。类似这种当一个变量有几种固定可能的取值时，就可以将它定义为枚举类型。

在 JDK 1.5 之前没有枚举类型，那时候一般用接口常量来替代。而使用 Java 枚举类型 enum 可以更贴近地表示这种常量。

#### **一、声明定义枚举**

声明枚举时必须使用 enum 关键字，然后定义枚举的名称、可访问性、基础类型和成员等。枚举声明的语法如下：

```
enum-modifiers enum enumname:enum-base
{
    enum-body,
}
```

**其中：**

enum-modifiers 表示枚举的修饰符主要包括 public、private 和 internal；

enumname 表示声明的枚举名称；

enum-base 表示基础类型；如果没有显式地声明基础类型的枚举，那么意味着它所对应的基础类型是 int。

enum-body 表示枚举的成员，它是枚举类型的命名常数。任意两个枚举成员不能具有相同的名称，且它的常数值必须在该枚举的基础类型的范围之内，多个枚举成员之间使用逗号分隔。

**例 1:**

下面代码定义了一个表示性别的枚举类型 SexEnum ，一个表示颜色的枚举类型 Color。

```
public enum SexEnum {
    male,female;
}
public enum Color {
    RED,BLUE,GREEN,BLACK;
}
```

之后便可以通过枚举类型名直接引用常量，如 SexEnum.male、Color.RED。

使用枚举还可以使 switch 语句的可读性更强，例如以下示例代码：

```
enum Signal {
    //定义一个枚举类型
    GREEN,YELLOW,RED
}
public class TrafficLight {
    Signal color=Signal.RED;
    public void change() {
        switch(color) {
            case RED:
                color=Signal.GREEN;
                break;
            case YELLOW:
                color=Signal.RED;
                break;
            case GREEN:
                color=Signal.YELLOW;
                break;
        }
    }
}
```

#### **二、枚举类详解**

**Java 中的每一个枚举都继承自 java.lang.Enum 类**。当定义一个枚举类型时，每一个枚举类型成员都可以看作是 Enum 类的实例，这些枚举成员默认都被 final、public, static 修饰，当使用枚举类型成员时，直接使用枚举名称调用成员即可。

所有枚举实例都可以调用 Enum 类的方法，常用方法如表 1 所示。

表1 Enum类的常用方法

| 方法名称    | 描述                             |
| ----------- | -------------------------------- |
| values()    | 以数组形式返回枚举类型的所有成员 |
| valueOf()   | 将普通字符串转换为枚举实例       |
| compareTo() | 比较两个枚举成员在定义时的顺序   |
| ordinal()   | 获取枚举成员的索引位置           |

**例 1：**

通过调用枚举类型实例的 values() 方法可以将枚举的所有成员以数组形式返回，也可以通过该方法获取枚举类型的成员。

下面的示例创建一个包含 3 个成员的枚举类型 Signal，然后调用 values() 方法输出这些成员。

```
enum Signal {
    //定义一个枚举类型
    GREEN,YELLOW,RED;
}
public static void main(String[] args) {
    for(int i=0;i<Signal.values().length;i++) {
        System.out.println("枚举成员："+Signal.values()[i]);
    }
}
```

输出结果如下：

```
枚举成员：GREEN
枚举成员：YELLOW
枚举成员：RED
```

#### **三、为枚举添加方法**

Java 为枚举类型提供了一些内置的方法，同时枚举常量也可以有自己的方法。此时要注意必须在枚举实例的最后一个成员后添加分号，而且必须先定义枚举实例。

**例 4：**

下面的代码创建了一个枚举类型 WeekDay，而且在该类型中添加了自定义的方法。

```
enum WeekDay {
    Mon("Monday"),
    Tue("Tuesday"),
    Wed("Wednesday"),
    Thu("Thursday"),
    Fri("Friday"),
    Sat("Saturday"),
    Sun("Sunday");
    //以上是枚举的成员，必须先定义，而且使用分号结束
    private final String day;
    private WeekDay(String day) {
        this.day=day;
    }
    public static void printDay(int i) {
        switch(i) {
            case 1:
                System.out.println(WeekDay.Mon);
                break;
            case 2:
                System.out.println(WeekDay.Tue);
                break;
            case 3:
                System.out.println(WeekDay.Wed);
                break;
            case 4:
                System.out.println(WeekDay.Thu);
                break;
            case 5:
                System.out.println(WeekDay.Fri);
                break;
            case 6:
                System.out.println(WeekDay.Sat);
                break;
            case 7:
                System.out.println(WeekDay.Sun);
                break;
            default:
                System.out.println("wrong number!");
        }
    }
    public String getDay(){
        return day;
    }
}
```

上面代码创建了 WeekDay 枚举类型，下面遍历该枚举中的所有成员，并调用 printDay() 方法。示例代码如下：

```
public static void main(String[] args) {
    for(WeekDay day:WeekDay.values()) {
        System.out.println(day+"====>"+day.getDay());
    }
    WeekDay.printDay(5);
}
```

```
Mon====>Monday
Tue====>Tuesday
Wed====>Wednesday
Thu====>Thursday
Fri====>Friday
Sat====>Saturday
Sun====>Sunday
Fri
```

Java 中的 enum 还可以跟 Class 类一样覆盖基类的方法。下面示例代码创建的 Color 枚举类型覆盖了 toString() 方法。

```
public class Test {
    public enum Color {
        RED("红色",1),GREEN("绿色",2),WHITE("白色",3),YELLOW("黄色",4);
        //成员变量
        private String name;
        private int index;
        //构造方法
        private Color(String name,int index) {
            this.name=name;
            this.index=index;
        }
        //覆盖方法
        @Override
        public String toString() {
            return this.index+"-"+this.name;
        }
    }
    public static void main(String[] args) {
        System.out.println(Color.RED.toString());    //输出：1-红色
    }
}
```

