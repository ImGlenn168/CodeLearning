1. 基本类型

|     数据类型名称      | 占用字节 |    默认值     |                        长度                         |  封装类   |
| :-------------------: | :------: | :-----------: | :-------------------------------------------------: | :-------: |
|     byte (字节型)     |    1     |       0       |                      -128到127                      |   Byte    |
|    short (短整型)     |    2     |       0       |                    -32768到32767                    |   Short   |
|      int (整型)       |    4     |       0       |               -2147483648到2147483647               |  Integer  |
|     long(长整型)      |    8     |      0L       |      -9223372036854775808到9223372036854775807      |   Long    |
|    float (浮点型)     |    4     |     0.0f      |           -3.40282347E+38到3.40282347E+38           |   Float   |
| double (双精度浮点型) |    8     |       0       | -1.79769313486231570E+308到1.79769313486231570E+308 |  Double   |
|     char (字符型)     |    2     | \u0000 (空格) |                      0到65535                       | Character |
|   boolean (布尔型)    |    1     |     false     |                     true和false                     |  Boolean  |

```java
byte myByte = 10;
System.out.println(myByte);

short myShort = 1000;
System.out.println(myShort);

int myInt = 100000;
System.out.println(myInt);


long myLong = 10000000000L;
System.out.println(myLong);

float myFloat = 3.14f;
System.out.println(myFloat);

double myDouble = 3.14159;
System.out.println(myDouble);

char myChar = 'A';
System.out.println(myChar);

boolean myBoolean = true;
System.out.println(myBoolean);
```



2. 引用类型

引用数据类型是指一种存储对象的引用的数据类型，它们不直接存储数据，而是存储数据的地址。常见的引用数据类型有类、接口、数组等。

```
String myString = "Hello, World!";
System.out.println(myString);

// 在上面的代码中，myString是一个String类型的引用变量，它引用了一个存储字符串数据的对象。
```

