# java的文件读写

## File 类常用方法

File 类的对象代表操作系统的文件（文件、文件夹），File 类在 java.io.File 包下。
File 类提供了诸如：创建文件对象代表文件，获取文件信息（大小、修改时间）、删除文件、创建文件（文件夹）等功能。

### 一、File 类的创建

**注意：**
File 对象可以定位文件和文件夹；File(String pathname) 封装的对象仅仅是一个路径名，这个路径可以是存在的，也可以是不存在的。

**路径分隔符的三种写法：**

```java
File file = new File("E:\\谷歌下载\\lufei.jpg");
File file = new File("E:/谷歌下载/lufei.jpg");
File file = new File("E:" + File.separator+"谷歌下载"+ File.separator +"lufei.jpg");
```

**绝对路径和相对路径：**

```java
// 绝对路径：从盘符开始
File f1 = new File("E:\\IdeaDocument\\WORK\\Javasepromax\\day09-oop\\src\\data.txt");
// 相对路径：一般定位模块中的文件的，即相对到工程下
File f2 = new File("day09-oop\\src\\data.txt");
```

### 二、常用方法：判断文件类型、获取文件信息功能

示例：

```java
public class FileDemo01 {
    public static void main(String[] args) {
        // 1.绝对路径创建一个文件对象
        File f1 = new File("E:/code/lufei.jpg");
        // a.获取它的绝对路径。
        System.out.println(f1.getAbsolutePath()); // E:\code\lufei.jpg
        // b.获取文件定义的时候使用的路径。
        System.out.println(f1.getPath());  // E:\code\lufei.jpg
        // c.获取文件的名称：带后缀。
        System.out.println(f1.getName()); // lufei.jpg
        // d.获取文件的大小：字节个数。
        System.out.println(f1.length()); // 38899 字节大小
        // e.获取文件的最后修改时间
        long time = f1.lastModified();
        System.out.println("最后修改时间：" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time));
        // f、判断文件是文件还是文件夹
        System.out.println(f1.isFile()); // true
        System.out.println(f1.isDirectory()); // false
    }
}
```

```java
public class FileDemo02 {
    public static void main(String[] args) {
        File f2 = new File("D:/");
        System.out.println(f2.isFile()); // false
        System.out.println(f2.isDirectory()); // true
        System.out.println(f2.exists()); // true

        File f3 = new File("D:/aaa"); // 不存在该文件夹
        System.out.println(f3.isFile()); // false
        System.out.println(f3.isDirectory()); // false
        System.out.println(f3.exists()); // false
    }
}
```

### 三、常用方法：创建文件、删除文件功能

delete 方法默认只能删除文件和空文件夹，且 delete 方法直接删除不走回收站。

```java
public class FileDemo03 {
    public static void main(String[] args) throws IOException {
        File f = new File("day09-oop\\src\\data.txt");
        // a.创建新文件，创建成功返回 true ,反之返回 false
        // 已经存在则不创建，返回false
        System.out.println(f.createNewFile()); // false
        File f1 = new File("day09-oop\\src\\data02.txt");
        System.out.println(f1.createNewFile()); // （几乎不用的，因为以后文件都是自动创建的！）

        // b.mkdir创建一级目录
        File f2 = new File("D:/resources/aaa");
        System.out.println(f2.mkdir()); // false，
        // 如果不存在 resources 文件夹，aaa不会创建成功。如果存在 resources 文件夹，则创建成功，体现“一级”
        
        // c.mkdirs创建多级目录(重点)
        File f3 = new File("D:/resources/ccc/ddd/eee/ffff");
        System.out.println(f3.mkdirs()); // 支持多级创建

        // d.删除文件或者空文件夹
        System.out.println(f1.delete());
        File f4 = new File("D:/resources");
        System.out.println(f4.delete()); // 占用一样可以删除

        // 只能删除空文件夹，不能删除非空文件夹.
		// File f5 = new File("D:/resources/aaa");
		// System.out.println(f5.delete());
    }
}
```

### 四、File 类的遍历功能

**注意：**

```java
① 当文件不存在时或者代表文件时，返回null，而不是[ ]。
② 当文件对象代表一个空文件夹时，返回一个长度为0的数组。
③ 当文件对象是一个有内容的文件夹时，将里面所有文件和文件夹的路径放在File数组中返回 (正常情况)。
⑤ 当文件对象是一个有隐藏文件的文件夹时，将里面所有文件和文件夹的路径放在File数组中返回，包含隐藏文件。
⑥ 当没有权限访问该文件夹时，返回null。
```

**示例：**

```java
public class FileDemo04 {
    public static void main(String[] args) {
        File f1 = new File("D:/resources");
        // 1. 获取目录下所有的“一级文件名称”到一个字符串数组
        String[] names = f1.list();
        for (String name : names) {
            System.out.println(name);
        }

        // 2. 获取当前目录下所有的"一级文件对象"到一个文件对象数组中去返回（重点）
        File[] files = f1.listFiles();
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }

        // 注意事项：不存在该文件
        File dir = new File("D:\\resources\\aa\\sdasd");
        File[] files1 = dir.listFiles();
        System.out.println(Arrays.toString(files1));
    }
}
```

### 五、应用递归实现文件搜索

**需求：** 文件搜索，从 D 盘中搜索出某个文件名称并输出绝对路径。

**分析：** ① 先定位出的应该是一级文件对象
   ② 遍历全部一级文件对象，判断是否是文件
   ③ 如果是文件，判断是否是自己想要的
   ④ 如果是文件夹，需要继续递归进去重复上述过程

**实现：**

```java
public class FileDemo05 {
    public static void main(String[] args) {
        // 2、传入目录 和  文件名称
        searchFile(new File("D:/") , "sublime_text.exe");
    }

    /**
     * 1、搜索某个目录下的全部文件，找到我们想要的文件。
     * @param dir  被搜索的源目录
     * @param fileName 被搜索的文件名称
     */
    public static void searchFile(File dir,String fileName){
        // 3、判断dir是否是目录
        if(dir != null && dir.isDirectory()){
            // 可以找了
            // 4、提取当前目录下的一级文件对象
            File[] files = dir.listFiles(); // null  []
            // 5、判断是否存在一级文件对象，存在才可以遍历
            if(files != null && files.length > 0) {
                for (File file : files) {
                    // 6、判断当前遍历的一级文件对象是文件 还是 目录
                    if(file.isFile()){
                        // 7、是不是咱们要找的，是把其路径输出即可
                        if(file.getName().contains(fileName)){
                            System.out.println("找到了：" + file.getAbsolutePath());
                            // 启动它。
                            try {
                                Runtime r = Runtime.getRuntime();
                                r.exec(file.getAbsolutePath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        // 8、是文件夹，需要继续递归寻找
                        searchFile(file, fileName);
                    }
                }
            }
        }else {
            System.out.println("对不起，当前搜索的位置不是文件夹！");
        }
    }
}
```

### 六、应用递归实现删除非空文件夹

**需求：** 删除非空文件夹

**分析：** ① File默认不可以删除非空文件夹；② 我们需要遍历文件夹，先删除里面的内容，再删除自己。

**实现：**

```java
public class FileDemo06 {
    public static void main(String[] args) {
        deleteDir(new File("D:/new"));
    }

    /**
       删除文件夹，无所谓里面是否有内容，都可以删除
     * @param dir
     */
    public static void deleteDir(File dir){
        // 1、判断dir存在且是文件夹
        if(dir != null && dir.exists() && dir.isDirectory()){
            // 2、提取一级文件对象。
            File[] files = dir.listFiles();
            // 3、判断是否存在一级文件对象，存在则遍历全部的一级文件对象去删除
            if(files != null && files.length > 0){
                // 里面有内容
                for (File file : files) {
                    // 4、判断file是文件还是文件夹，文件直接删除
                    if(file.isFile()){
                        file.delete();
                    }else {
                        // 递归删除
                        deleteDir(file);
                    }
                }
            }
            // 删除自己
            dir.delete();
        }
    }
}
```





## 文件读写

Java中如何以二进制字节的方式来处理文件，前面我们提到Java中有流的概念，以二进制方式读写的主要流有：

❑ InputStream/OutputStream：这是基类，它们是抽象类。

❑ FileInputStream/FileOutputStream：输入源和输出目标是文件的流。

❑ ByteArrayInputStream/ByteArrayOutputStream：输入源和输出目标是字节数组的流。

❑ DataInputStream/DataOutputStream：装饰类，按基本类型和字符串而非只是字节读写流。

❑ BufferedInputStream/BufferedOutputStream：装饰类，对输入输出流提供缓冲功能。

下面，我们就来介绍这些类的功能、用法、原理和使用场景，最后总结一些简单的实用方法

```java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wcfb
 * @time 2021/5/8/008.
 * @since
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPO implements Serializable {
    private String name;
    private int age;
    private int sex;
}
```

```java
import com.wcfb.model.po.StudentPO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wcfb
 * @time 2021/5/8/008.
 * @desc 文件流测试
 * @since 1.0
 */
@SpringBootTest
public class FileTestApplication {

    /**
     * 写内容到文件
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        OutputStream outputStream = new FileOutputStream("text.txt");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append("hello io ").append(i).append("\n");
        }
        String data = stringBuilder.toString();
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }
    }

    /**
     * 读取文件内容（固定长度）
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        InputStream inputStream = new FileInputStream("text.txt");
        byte[] buf = new byte[1024];
        try {
            int byteRead = inputStream.read(buf);
            String data = new String(buf, 0, byteRead, StandardCharsets.UTF_8);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    /**
     * 读取所有内容
     */
    @Test
    public void test3() throws Exception {
        InputStream inputStream = new FileInputStream("text.txt");
        byte[] buf = new byte[1024];
        int byteRead = -1;
        while ((byteRead = inputStream.read(buf)) != -1) {
            String data = new String(buf, 0, byteRead, StandardCharsets.UTF_8);
            System.out.println(data);
        }
    }

    /**
     * 保存对象
     *
     * @throws FileNotFoundException
     */
    @Test
    public void test4() throws Exception {
        List<StudentPO> studentList = Arrays.asList(
                new StudentPO("小红", 16, 0),
                new StudentPO("小兰", 17, 1),
                new StudentPO("小黑", 22, 1)
        );
        DataOutput dataOutput = new DataOutputStream(new FileOutputStream("student.txt"));
        for (StudentPO s : studentList) {
            dataOutput.writeUTF(s.getName());
            dataOutput.writeInt(s.getAge());
            dataOutput.writeInt(s.getSex());
        }
    }

    /**
     * 读取对象(文件流格式)
     */
    @Test
    public void test5() throws Exception {
        DataInput input = new DataInputStream(new FileInputStream("student.txt"));
        List<StudentPO> studentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            studentList.add(new StudentPO(input.readUTF(), input.readInt(), input.readInt()));
        }
        studentList.forEach(System.out::println);
    }

    /**
     * 使用writer
     */
    @Test
    public void test6() throws Exception {
        Writer writer = new OutputStreamWriter(new FileOutputStream("student.txt"), StandardCharsets.UTF_8);
        String data = "hello io 试试";
        try {
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    /**
     * 使用reader
     */
    @Test
    public void test7() throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream("student.txt"), StandardCharsets.UTF_8);
        try {
            char[] cbuf = new char[1024];
            int chatsRead = reader.read(cbuf);
            System.out.println(new String(cbuf, 0, chatsRead));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    /**
     * fileWriter使用
     *
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {
        Writer writer = new FileWriter("student.txt");
        try {
            writer.write("writer is ok a");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    /**
     * 使用 fileReader
     *
     * @throws Exception
     */
    @Test
    public void test9() throws Exception {
        Reader reader = new FileReader(new File("student.txt"));
        try {
            char[] chars = new char[1024];
            int read = reader.read(chars);
            System.out.println(new String(chars));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * fileWriter使用
     *
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {
        Writer writer = new FileWriter("student.txt");
        try {
            writer.write("writer is ok a");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }


        Reader reader = new FileReader(new File("student.txt"));
        try {
            char[] chars = new char[1024];
            int read = reader.read(chars);
            System.out.println(new String(chars));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }


    /**
     * 使用bufferWriter
     *
     * @throws Exception
     */
    @Test
    public void test10() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("student.txt"));
        try {
            writer.write("你好，" + "hhhh 哈哈哈\n guguhhhh ");
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

        BufferedReader reader = new BufferedReader(new FileReader("student.txt"));
        try {
            String s = null;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

}
```

