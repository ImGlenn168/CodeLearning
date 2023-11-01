# Scanner 类

## 1.概述

（1）Scanner 类是 Java 中一个用于读取用户输入的工具类。它可以从多种输入源读取数据，例如标准输入流、文件或字符串。Scanner 类提供了一系列方法来处理不同数据类型的输入，比如整数、浮点数、布尔值、字符和字符串等。

（2）Scanner 类中有两种比较重要的方法：

以 hasNext 开头的方法：用于检查输入源中是否还有下一个标记可用，常用的有：
hasNext()：如果输入源中还有下一个标记（非空格字符），返回 true。
hasNextLine()：如果输入源中还有下一行（非空行），返回 true。
hasNextInt()：如果输入源中还有下一个整数，返回 true。
hasNextDouble()：如果输入源中还有下一个浮点数，返回 true。
hasNextBoolean()：如果输入源中还有下一个布尔值，返回 true。
以 next 开头的方法：用于从输入源中获取下一个标记并返回相应的数据类型，常用的有：
next()：从输入源中获取并返回一个字符串，默认以空格为分隔符，以回车 Enter 为结束符，回车 Enter 后的内容则放入缓冲区。
nextLine()：从输入源中获取并返回一行字符串（以换行符为分隔符）。
nextInt()：从输入源中获取并返回一个整数。
nextDouble()：从输入源中获取并返回一个浮点数。
nextBoolean()：从输入源中获取并返回一个布尔值（“true” 或 “false”）。
（3）next() 和 nextLine() 在处理方式和使用场景方面有一些区别：

next() 方法：
读取的字符串不包含分隔符，如果输入中存在多个以空格分隔的单词，next() 方法只会返回第一个单词。
在读取之前会忽略输入中的前导空格。
nextLine() 方法：
从输入源中读取并返回一整行字符串，包括换行符 \n 在内。
它会读取输入源中的全部内容直到遇到换行符，或者输入结束。
nextLine() 方法返回的字符串可以包含空格和其他特殊字符。

## 2.使用举例

### 2.1.从不同的输入源读取数据

当使用 Scanner 类时，可以从不同的输入源读取数据，包括标准输入流、文件和字符串等。下面是几个使用不同输入源的示例：

**（1）从标准输入流读取数据**

```
public class Example {
    public static void main(String[] args) {
        // 创建 Scanner 对象，使用标准输入流作为输入源
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入一个整数：");
        int number = scanner.nextInt(); // 从标准输入流读取整数

        System.out.println("你输入的整数是：" + number);

        scanner.close();
    }
}
```

在这个示例中，我们创建了一个 Scanner 对象，并将标准输入流（System.in）作为输入源。然后我们使用 `nextInt()` 方法从标准输入流读取一个整数。

**（2）从文件读取数据**

```
public class Example {
    public static void main(String[] args) {
        try {
            // 创建 Scanner 对象，使用文件作为输入源
            Scanner scanner = new Scanner(new File("input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // 从文件读取一行数据
                System.out.println(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

在这个示例中，我们创建了一个 Scanner 对象，并将文件 “input.txt” 作为输入源。然后我们使用 `hasNextLine()` 和 `nextLine()` 方法循环读取文件的每一行数据并打印出来。

**（3）从字符串读取数据**

```
public class Example {
    public static void main(String[] args) {
        String input = "Hello World 123";
        // 创建 Scanner 对象，使用字符串作为输入源
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt(); // 从字符串读取整数
                System.out.println("整数：" + number);
            } else {
                String word = scanner.next(); // 从字符串读取单词
                System.out.println("单词：" + word);
            }
        }
        scanner.close();
    }
}
```

在这个示例中，我们创建了一个 Scanner 对象，并将字符串 “Hello World 123” 作为输入源。然后我们使用 `hasNext()`、`hasNextInt()` 和 `next()` 方法循环读取字符串中的每个单词或整数，并打印出来。

### 2.2.next() 和 nextLine() 的区别

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // next() && nextLine()
        System.out.println("请输入一个字符串 nextLine():");
        String str1 = scanner.nextLine();
        System.out.println(str1);

        System.out.println("请输入一个字符串 next():");
        String str2 = scanner.next();
        System.out.println(str2);
    }
}
```

结果如下：

```
请输入一个字符串 nextLine():
   Hello World  
nextLine() 的读取结果为: 
   Hello World  

请输入一个字符串 next():
   Hello   World
next() 的读取结果为: 
Hello
```

### 2.3.读取大小已知的一维数组

描述：第一行输入是以一个整数 n，表示数组 nums 的长度，第 2 行输入 n 个整数，整数之间用空格分隔。请将这些整数保存到数组 nums 中，然后将其打印出来。例如

```
7
4 9 0 -1 6 8 9
```

（1）使用 `nextInt()` 逐个读取：

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //数组的长度
        int n = scanner.nextInt();
        int[] nums = new int[n];
        //数组中的 n 个数
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println("数组 nums 的元素为:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}
```

结果如下：

```
7
4 9 0 -1 6 8 9
数组 nums 的元素为:
4 9 0 -1 6 8 9 
```

（2）使用 `nextLine()` 先全部读取，然后逐个解读：

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //清除缓冲区中的换行符
        scanner.nextLine();
        int[] nums = new int[n];
        String input = scanner.nextLine();
        //以一个或者多个空格为分隔符
        String[] numStrings = input.split("\\s+");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(numStrings[i]);
        }
        System.out.println("数组 nums 的元素为：");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}
```

### 2.4.读取大小未知的一维数组

描述：输入若干个整数，整数之间用空格分隔。请将这些整数保存到数组 nums 中，然后将其打印出来。例如：

```
1 12 3 4 5 6 
```

（1）使用 `nextInt()` 逐个读取：

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = new ArrayList<>();
        while (scanner.hasNextInt()) {
            nums.add(scanner.nextInt());

            if (nums.size() == 4) {
                break;
            }
        }
        System.out.println("数组 nums 的元素为：");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}
```

注意事项：

- 如果在控制台手动输入若干个整数，期望手动停止输入后，程序结束读取整数，可以使用其他方式来指示结束输入的条件。一种常见的方式是通过nums.size()来判断是否结束循环；
- 由于是逐个读取，因此事先不知道数组的长度，所以上面代码中使用 list 来存储；

（2）使用 `nextLine()` 先全部读取，然后逐个解读：

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.trim();
        String[] numStrings = input.split("\\s+");
        int[] nums = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            nums[i] = Integer.parseInt(numStrings[i]);
        }
        System.out.println("\n数组 nums 的元素为:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}
```

结果如下：

```
1 12 3 4 5 6 

数组 nums 的元素为:
1 12 3 4 5 6 
```



### 2.5.读取长度大小已知的二维数组

描述：第一行输入是两个整数 m 和 n，中间用空格分隔，后面跟随 m 行，每行都有 n 个整数，整数之间用空格分隔。例如：

```
3 4
1 8 7 3
2 3 4 5
4 5 6 7
```

（1）使用 `next()` 读取：

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取整数 m 作为二维数组的长度
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        //创建二维数组 nums
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = scanner.nextInt();
            }
        }
        //打印二维数组 nums
        System.out.println("\n数组 nums 的元素为:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```

（2）使用 `nextLine()` 读取：

```
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取整数 m 作为二维数组的长度
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        //创建二维数组 nums
        int[][] nums = new int[m][n];
        //读取 m 行数据到二维数组 nums
        for (int i = 0; i < m; i++) {
            //读取整行数据
            String line = scanner.nextLine();
            //将每个整数分割保存到一维数组
            String[] values = line.split("\\s+");
            //创建长度为 n 的一维数组
            nums[i] = new int[n];
            //保存每个整数到一维数组
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(values[j]);
            }
        }
        //打印二维数组 nums
        System.out.println("\n数组 nums 的元素为:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```

结果如下：

```
3 4
1 8 7 3
2 3 4 5
4 5 6 7

数组 nums 的元素为:
1 8 7 3 
2 3 4 5 
4 5 6 7 
```

### 2.6.读取长度大小未知的二维锯齿数组

描述：输入若干行，每一行有若干个整数，整数之间用空格分隔，并且每一行的整数个数不一定相同。例如：

```
1 2 3 45 
3 4 
9 0 -1 2 
1 4 6 
4 6 7 8 7 
```

```
class Solution {
    public static void main(String[] args) {          
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> nums = new ArrayList<>();
        // 逐行读取数据并保存到二维数组锯齿 nums
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break; // 输入结束
            }
            String[] values = line.split("\\s+");
            List<Integer> row = new ArrayList<>();
            for (String value : values) {
                row.add(Integer.parseInt(value));
            }
            nums.add(row);
        }
        //打印二维锯齿数组 nums
        System.out.println("二维锯齿数组 nums 的元素为:");
        for (List<Integer> row : nums) {
            for (Integer value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
```

结果如下：

```
1 2 3 45
3 4
9 0 -1 2
1 4 6
4 6 7 8 7

二维锯齿数组 nums 的元素为:
1 2 3 45 
3 4 
9 0 -1 2 
1 4 6 
4 6 7 8 7 
```

