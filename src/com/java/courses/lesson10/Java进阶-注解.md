# Java 自定义注解

像平时用到的 springboot、mybatis 等框架提供了许多的注解，免去了许多配置文件的繁琐工作，大大简便了开发，Java 提供了自定义注解的功能，这里就先展示简单的例子。

### 1、注解的作用

**注解可以看作是一种特殊的标记，可以用在方法、类、参数和包上，程序在编译或者运行时可以检测到这些标记而进行一些特殊的处理，例如标注在方法上可以实现接口权限的校验。**

**使用场景**：自定义注解+拦截器或者 AOP。

**声明方式**：通过关键字 @interface 声明为注解，例子如下：

```
public @interface SystemConfig {
    
}

```

### 2、注解的元素类型

主要有@Target，@Retention,@Document,@Inherited 用来修饰注解。

#### 2.1、@Target

表明该注解可以应用的java元素类型。

| **Target类型**              | **描述**                                                     |
| --------------------------- | ------------------------------------------------------------ |
| ElementType.TYPE            | 应用于类、接口（包括注解类型）、枚举                         |
| ElementType.FIELD           | 应用于属性（包括枚举中的常量）                               |
| ElementType.METHOD          | 应用于方法                                                   |
| ElementType.PARAMETER       | 应用于方法的形参                                             |
| ElementType.PARAMETER       | 应用于方法的形参                                             |
| ElementType.LOCAL_VARIABLE  | 应用于局部变量                                               |
| ElementType.ANNOTATION_TYPE | 应用于注解类型                                               |
| ElementType.PACKAGE         | 应用于包                                                     |
| ElementType.PACKAGE         | 应用于包                                                     |
| ElementType.TYPE_USE        | 1.8版本新增，应用于任何使用类型的语句中（例如声明语句、泛型和强制转换语句中的类型） |

#### 2.2、@Retention

表明该注解的生命周期。

| 生命周期类型            | 描述                                             |
| ----------------------- | ------------------------------------------------ |
| RetentionPolicy.SOURCE  | 编译时被丢弃，不包含在类文件中                   |
| RetentionPolicy.CLASS   | JVM加载时被丢弃，包含在类文件中，默认值          |
| RetentionPolicy.RUNTIME | 由JVM 加载，包含在类文件中，在运行时可以被获取到 |

#### 2.3、@Documented

表明该注解标记的元素可以被Javadoc 或类似的工具文档化。

#### 2.4、@Inherited

表明使用了@Inherited注解的注解，所标记的类的子类也会拥有这个注解

### 3、自定义注解

#### 3.1、编写注解

这里声明一个 `SystemConfig` 的注解：

```
//该注解可以应用于类、接口（包括注解类型）、枚举
@Target(ElementType.TYPE)
//该注解标记的元素可以被Javadoc 或类似的工具文档化
@Documented
//该注解的生命周期，由JVM 加载，包含在类文件中，在运行时可以被获取到
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemConfig {
    String name() default "lin";
    int age();
}
```

#### 3.2、应用注解

在 `User` 类上应用刚刚编写的 `SystemConfig` 注解：

```
//应用SystemConfig注解到User类
@SystemConfig(age = 20)
public class User {
    private String name;

    private Integer age;
}
```

### 3.3、测试

编写测试类，测试注解是否生效：

```
public class AnnotationTest {

    public static void main(String[] args) {
        //获取User的Class对象
        Class<?> userClass = User.class;
        //判断Class对象上是否有SystemConfig的注解
        if (userClass.isAnnotationPresent(SystemConfig.class)) {
            System.out.println("User上配置了SystemConfig注解");
            //获取SystemConfig注解
            SystemConfig config = userClass.getAnnotation(SystemConfig.class);
            System.out.println("SystemConfig.name:" + config.name() + "; SystemConfig.age:" + config.age());
        } else {
            System.out.println("User上没有配置SystemConfig注解");
        }
    }
}

```

打印结果如下：

```
User上配置了SystemConfig注解
SystemConfig.name:lin; SystemConfig.age:20
```

这里表明刚刚编写的注解成功生效了。