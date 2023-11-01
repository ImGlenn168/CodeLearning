# 条件语句

条件语句可以根据不同的条件执行不同的语句。条件语句包括if语句和switch语句。



## 1 if 条件语句

  if条件语句是一个重要的编程语句，用于告诉程序在某个条件成立的情况下执行某段语句，而在另一种情况下执行另外的语句。
  if使用if条件语句可以选择是否执行紧跟在条件之后的那个语句。关键字if 之是作为条件的“布尔表达式‘如果该表达式返回的结果为true，则执行其后的语句；若为false,则不执行if条件之后的语句。
if条件语句可以分为 简单的**if**语句、**if…else**语句和**if…else if** 多分支语句。

1. 简单的if语句
   语法如下：

   ```
   if(布尔表达式){//布尔表达式：必要参数，表示最后返回的结果必须时是一个布尔值。
         语句序列；//可选参数，可以是一条或者多条语句。
   }
   ```

例如：

```
int a =100;
      if (a==100)
      System.out.println("a的值是100")；//此处虽然if后面的复合语句只有一条语句，省略{}并无错误但是最好不要省略。
```

例：在项目中创建类Getif，在主方法中定义整型变量。使用条件语句判断两个变量的大小来决定输出结果。

```
public class GetIf {
	public static void main(String[] args) {
		int x=45;
		int y=12;
		if (x>y) {//如果条件成立。输出信息
			System.out.println("变量x大于y");
		}
		if (x<y) {//如果条件成立，输出信息
			System.out.println("变量y小于x");
		}
	}
}
```

## 2 if…else语句

如果满足某种条件，就进行某种处理，否则就进行另一种处理。
语法：

```
if(表达式){
     若干语句；
}
else{
     若干语句；
}
```

例：在项目中创建Getifelse,在主方法中定义变量。并通过使用if…else语句判断变量的值来决定输出结果

```
public class Getifelse {
	public static void main(String[] args) {
		int math=90;
		int english=50;
		if(math>60) {
			System.out.println("数学及格了");
		}
			else{
				System.out.println("数学不及格");
			}
		if(english>60) {
			System.out.println("英语及格了");
		}
		else {
			System.out.println("英语不及格");	
		}
	}

}
```

## 3 if…else if 多分支语句

if…else if 多分支语句用于针对某一事件的多种情况进行处理。通常表现为“如果满足某种条件，就进行某种处理，否则如果满足另一种条件就进行另一种处理”
语法：

```
if(条件表达式1){
    语句序列1
}
else if(条件表达式2){
    语句序列2
}
...
else if(条件表达式n){
    语句序列n
}
```

例：在项目中创建类GetTerm,在主方法中定义变量x，使用if…else if多分支语句通过控制x的值来决定输出结果。

```
public class GetTerm {
	public static void main(String[] args) {
		int x=20;
		if(x>30) {
			System.out.println("x的值大于30");
		}
		else if(x==30) {
			System.out.println("x的值等于30");	
		}
		else if(x<30) {
			System.out.println("x的值小于30");
		}
	}

}
```

## 4 switch语句

在Java中，可以用switch语句将动作组织起来，以一个简单明了的方式实现“多选一”的选择
语法：

```
switch(表达式){
case 常量1：
     语句块1
     break;
 ...
case 常量n：
      语句块n
      break;
default:
      语句块n+1
      break;
}
```

例：要通过switch语句根据字符串str的值，输出不同的提示信息可以使用如下的代码

```
String str="周一"
switch(str){//同一个switch语句，case的常量必须互不相同
case "周一"
     System.out.println("今天是周一");
     break;
case "周二"
    System.out.println("今天是周二"); 
    break;
default:
     System.out.println("以上什么都不是");
}
```

例：在项目中创建类Getswitch，在主方法中应用switch语句将周一到周三的英文单词打印出来。

```
public class Getswitch {
	public static void main(String[] args) {
		int week=1;
		switch(week) {
		case 1:
			System.out.println("Mondy");
			break;
		case 2:
			System.out.println("Tuesday");
			break;
		case 3:
			System.out.println("Wednesday");
			break;
		default:
			System.out.println("None");
		}
	}
```

