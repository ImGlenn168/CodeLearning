## 数组

## 1. 数组基本用法

### 1.1 什么是数组

数组：存储**一组**相同数据类型的数据的集合。

注意事项: 在 Java 中, 数组中包含的变量必须是 相同类型.

### 1.2 创建数组

基本语法：

```Java
// 动态初始化
数据类型[] 数组名称 = new 数据类型 [] { 初始化数据 };
// 静态初始化
数据类型[] 数组名称 = { 初始化数据 };

```

代码示例：

```Java
int[] arr = new int[]{1, 2, 3};
int[] arr = {1, 2, 3};
int arr[] = {1, 2, 3};//和 C语言更相似了.但是我们还是更推荐写成 int[] arr 的形式. int和 [] 是一个整体.
int[] array = {1,2,3,4,5,6,7};//定一个数组并初始化
int[] array2 = new int[3];//代表我们创建了一个可以存放3个整形的数组 默认值为全0
int[] array2 = new int[]{1,2,3,4,5};//代表我们创建了一个可以存放5个整形的数组 并初始化为1 2 3 4 5
```

注意事项: 静态初始化的时候, 数组元素个数和初始化数据的格式是**一致**的.

### 1.3 数组的使用

代码示例: 获取长度 & 访问元素

```
public class TestDemo1 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};// 获取数组长度
        System.out.println("length: " + arr.length); // 执行结果: 3
        // 访问数组中的元素
        System.out.println(arr[1]); // 执行结果: 2
        System.out.println(arr[0]); // 执行结果: 1
        arr[2] = 100;
        System.out.println(arr[2]); // 执行结果: 100
    }
}
```

注意事项

1. 使用 arr.length 能够获取到数组的长度. . 这个操作为成员访问操作符. 后面在面向对象中会经常用到.(数组名+.length 可以得到数组的长度)

2. 使用 [ ] 按下标取数组元素. 需要注意, 下标从 0 开始计数

3. 使用 [ ] 操作既能读取数据, 也能修改数据.

4. 下标访问操作不能超出有效范围 [0, length - 1] , 如果超出有效范围, 会出现下标越界异常

代码示例: 下标越界

```Java
public class TestDemo1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(arr[100]);
    }
}
```

代码示例: 遍历数组

所谓 “遍历” 是指将数组中的所有元素都访问一遍, 不重不漏. 通常需要搭配循环语句.

代码示例: 使用 for-i 遍历数组

```
public class Test02 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

```

代码示例: 使用 for-each 遍历数组

```
public class TestDemo1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        for (int x : arr) {
            System.out.println(x);
        }
    }
}
```

## 2.数组作为方法的参数

### 2.1 基本用法

代码示例: 打印数组内容

```Java
import java.util.Arrays;//Arrays方法的包(头文件)

public class TestDemo {
    public static void printArray(int[] a) {
        for (int x : a) {
            System.out.println(x);
        }
    }
    
    public static void main(String[] args) {
            int[] arr = {1, 2, 3};
            printArray(arr);
            String ret = Arrays.toString(arr);
        System.out.println(ret);
    }
}

```

第一个是通过传参打印我们的数组

第二个则是通过我们的方法 Array.toString(数组名) 来打印我们的数组 注意这里我们是以**字符串**的形式来打印的

## 3. 数组作为方法的返回值

代码示例: 写一个方法, 将数组中的每个元素都 * 2

```Java
public class TestDemo {
    public static void func(int[] array){
        for (int i = 0; i < array.length; i++) {
            array[i] = 2*array[i];
            System.out.print(array[i] + " ");
        }
    }
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        func(array);
    }
}
```

这个代码固然可行, 但是破坏了原有数组. 有时候我们不希望破坏原数组, 就需要在方法内部创建一个新的数组, 并由方法返回出来.

因此我们引进一个新方法：

```
import java.util.Arrays;
public class TestDemo {
    public static int[] transform(int[] array){
        int[] ret =new int[array.length];
        for (int i = 0; i < array.length; i++) {
            ret[i] = 2*array[i];
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        int ret2[] = transform(array);
        System.out.println(Arrays.toString(ret2));
    }
}
```

这里我们使用了一个新的数组来返回我们所需要的值，没有破坏原有数组。

## 4. 数组练习

### 4.1 数组转字符串

我们实现一个自己版本的数组转字符串(myToString)

```Java
import java.util.Arrays;
public class TestDemo {
    public static String myToString(int[] array){
        if (array == null){
            return null;
        }
        String str = "[";
        for (int i = 0; i < array.length; i++) {
            str = str + array[i];
            if (i != array.length-1){
                str = str + ",";
            }
        }
        str = str + "]";
        return str;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        System.out.println(myToString(array));
    }
}
```

### 4.2 数组拷贝

```java
import java.util.Arrays;
public class TestDemo {
    public static int[] copyArray(int[] array){
        int[] copy = new int[array.length];//copy数组的长度
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,7,8,10};
        int[] ret = copyArray(array);
        System.out.println(Arrays.toString(ret));
    }
}
```

### 4.3 找数组中的最大元素

给定一个整型数组, 找到其中的最大元素 (找最小元素同理)

```java
public class TestDemo {
    public static int maxNum(int[] array){
        if (array == null){
            return -1;
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]){
                max = array[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] array = {12,8,1,2,10};
        System.out.println(maxNum(array));
    }
}
```

### 4.4 求数组中元素的平均值

```java
public class TestDemo {
    public static double avg(int[] array){
        int sum = 0;
        for (int x:array) {
            sum += x;
        }
        return (double)sum/(double)array.length;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6};
        System.out.println(avg(array));
    }
}
```

### 4.5 查找数组中指定元素(顺序查找)

```
public class TestDemo {
    public static int find(int[] arr, int toFind){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == toFind){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,10,5,6};
        System.out.println(find(arr,10));
    }
}
```

**4.6 查找数组中指定元素(二分查找)**

key是我们传的要找的参数

如果key比我们的中间值小，我们就取左半边即right = mid - 1；

如果key比我们的中间值大，我们就取右半边即 left = mid + 1；

```java
public class TestDemo {
    public static int binarySearch(int[] array,int key){
        int left = 0;
        int right = array.length-1;
        while (left <= right) {
            int mid = (left+right)/2;
            if (array[mid] > key){
                right = mid - 1;
            } else if(array[mid] < key){
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {1,2,4,7,15,17,21,32,55,88};
        int ret = binarySearch(array,17);
        System.out.println(ret);
    }
}
```

### 4.7 检查数组的有序性

```java
public class TestDemo {
    public static boolean isSorted(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,10,5,6};
        System.out.println(isSorted(array));
    }
}
```

### 4.8 数组排序(冒泡排序)

```java
public class TestDemo {
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            boolean flg = false;
            for (int j = 0; j < array.length-i-1; j++) {
                if(array[j] >array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            //flg == false
            if (!flg) {
                return;
            }
        }
    }
    public static void main(String[] args) {
        int[] array = {1,5,3,9,7,15,13,11};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
```

如果我们有10个数，我们需要交换9次，每次交换之后就会少比较一次 即-1

那么如果我们有n个数，我们就需要比较n-1次，每次交换之后我们就会少比较一次 即我们这里的j为 数组长度 - i - 1 这里的减去1是因为我们比较n个数实际只需要比较n-1次，所以我们要减去一个1 避免我们的数组越界 而减去i是因为我们没交换一次之后 最左边就会确定好一个数 我们下次比较的时候就可以不用再把它拿出来比较(即可以提高我们的程序效率)

这里用布尔boolean定义的flag是用来检查数组是否已经排序完成 如果已经排序好 便会直接返回原数组 增加程序的运行效率

这里我们还有一个方法来帮助我们排序：Array.sort(array);

```java
import java.util.Arrays;
public class TestDemo {
        public static void main(String[] args) {
        int[] array = {1,5,3,9,7,15,13,11};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }
}

```

这里我们可以看到，用方法排序更快 但是要注意我们要引入包。 `import java.util.Arrays;`

### 4.9 数组逆序

```java
public class TestDemo {
    public static void reverse(int[] array){
        int i = 0;
        int j = array.length-1;
        while (i < j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        reverse(array);
        System.out.println(Arrays.toString(array));
    }
}
```

### 4.10 数组数字排列

给定一个整型数组, 将所有的偶数放在前半部分, 将所有的奇数放在数组后半部分

```
public class TestDemo {
    public static void transfrom(int[] array){
        int left = 0;
        int right = array.length-1;
        while (left < right){
            //找奇数 停下来
            while (left < right && array[left] % 2 == 0){
                left++;
            }
            //找偶数 停下来
            while (left < right && array[right] % 2 != 0){
                right--;
            }
            //交换 奇数和偶数
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }
    }
    public static void main(String[] args) {
        int[] array = {1,3,5,4,7,10,9,8,6};
        transfrom(array);
        System.out.println(Arrays.toString(array));
    }
}
```

## 5. 二维数组

二维数组本质上也就是一维数组, 只不过每个元素又是一个一维数组.

基本语法：

```
数据类型[][] 数组名称 = new 数据类型 [行数][列数] { 初始化数据 };

int[][] array = {{1,2,3},{4,5,6}};
int[][] array2 = new int[][]{{1,2,3},{4,5,6}};
int[][] array3 = new int[2][3];
```

遍历方法一(两次for循环遍历)：

```
public class TestDemo {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                System.out.printf("%d\t", arr[row][col]);
            }
            System.out.println("");
        }
    }
}
```

打印二维数组是不用去数它的行数和列数 直接用 数组名.length 作为行数 数组[行] 作为列数来进行打印

打印方法二(foreach遍历)：

```
public class TestDemo {
    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        for (int[] ret:array) {
            for (int x:ret) {
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }
}
```

遍历方法三( 用Arrays.deepToString()来遍历 )：

```
import java.util.Arrays;
public class TestDemo {
    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(Arrays.deepToString(array));
    }
}
```

