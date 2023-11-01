# JAVA多线程详解

## 一、线程简介

### 1、进程、线程

- 程序：开发写的代码称之为程序。程序就是一堆代码，一组数据和指令集，是一个静态的概念。
- 进程(Process)：将程序运行起来，我们称之为进程。进程是执行程序的一次执行过程，它是动态的概念。进程存在生命周期，也就是说程序随着程序的终止而销毁。进程之间是通过TCP/IP端口实现交互的。
- 线程(Thread)：线程是进程中的实际运作的单位，是进程的一条流水线，是程序的实际执行者，是最小的执行单位。通常在一个进程中可以包含若干个线程，当然一个进程中至少有一个线程。线程是CPU调度和执行的最小单位。

**注意：**很多多线程都是模拟出来的，真正的多线程是指有多个CPU，即多核，如服务器，如果是模拟出来的多线程，即一个CPU的情况下，在同一个时间点，CPU只能执行一个代码，因为切换的很快，所以就有同时执行的错觉。

### 2、并发、并行、串行

- 并发：同一个对象被多个线程同时操作。（这是一种假并行。即一个CPU的情况下，在同一个时间点，CPU只能执行一个代码，因为切换的很快，所以就有同时执行的错觉）。
- 并行：多个任务同时进行。并行必须有多核才能实现，否则只能是并发。
- 串行：一个程序处理完当前进程，按照顺序接着处理下一个进程，一个接着一个进行。

### 3、进程的三态

进程在运行的过程中不断的改变其运行状态。通常一个运行的进程必须有三个状态，就绪态、运行态、阻塞态。

- 就绪态：当进程获取出CPU外所有的资源后，只要再获得CPU就能执行程序，这时的状态叫做就绪态。在一个系统中处于就绪态的进程会有多个，通常把这些排成一个队列，这个就叫就绪队列。
- 运行态：当进程已获得CPU操作权限，正在运行，这个时间就是运行态。在单核系统中，同一个时间只能有一个运行态，多核系统中，会有多个运行态。
- 阻塞态：正在执行的进程，等待某个事件而无法继续运行时，便被系统剥夺了CPU的操作权限，这时就是阻塞态。引起阻塞的原因有很多，比如：等待I/O操作、被更高的优先级的进程剥夺了CPU权限等。



## 二、线程实现

### 1、继承Thread类

```
 步骤：
 - 自定义线程类继承Thread类
 - 重写run()方法，编写线程执行体
 - 创建线程对象，调用start()方法启动线程（启动后不一定立即执行，抢到CPU资源才能执行）
```

代码如下（示例）：

```
// 自定义线程对象，继承Thread，重写run()方法
public class MyThread extends Thread {

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        // 线程执行体
        for (int i = 0; i < 10; i++) {
            System.out.println("我是自定义" + Thread.currentThread().getName() + "--" + i);
        }
    }

    public static void main(String[] args) {
        // main线程，主线程

        // 创建线程实现类对象
        MyThread thread = new MyThread("线程1");
        MyThread thread2 = new MyThread("线程2");
        // 调用start()方法启动线程
        thread.start();
        thread2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程--" + i);
        }
    }
}
```

### 2、实现Runnable接口

```
 步骤：
 - 自定义线程类实现Runnable接口
 - 实现run()方法，编写线程体
 - 创建线程对象，调用start()方法启动线程（启动后不一定立即执行，抢到CPU资源才能执行）
```

代码如下（示例）：

```

// 自定义线程对象，实现Runnable接口，重写run()方法
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // 线程执行体
        for (int i = 0; i < 10; i++) {
            System.out.println("我是自定义" + Thread.currentThread().getName() + "--" + i);
        }
    }

    public static void main(String[] args) {
        // main线程，主线程

        // 创建实现类对象
        MyRunnable myRunnable = new MyRunnable();
        // 创建代理类对象
        Thread thread = new Thread(myRunnable,"线程1");
        Thread thread2 = new Thread(myRunnable,"线程2");
        // 调用start()方法启动线程
        thread.start();
        thread2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程--" + i);
        }
    }
}
```

### 3、实现Callable接口（不常用）

```
 步骤：
 - 实现Callable接口，先要返回值类型
 - 重写call()方法，需要抛出异常
 - 创建目标对象
 - 创建执行服务：ExecutorService ser = Executor.newFixedThreadPool(1);
 - 提交执行：Future<Boolean> res = ser.submit(t1);
 - 获取结果：boolean r1 = res.get();
 - 关闭服务：ser.shutdownNow();
```

代码如下（示例）：

```
import java.util.concurrent.*;

// 自定义线程对象，实现Callable接口，重写call()方法
public class MyThread implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        // 线程执行体
        for (int i = 0; i < 10; i++) {
            System.out.println("我是自定义" + Thread.currentThread().getName() + "--" + i);
        }

        return true;
    }

    public static void main(String[] args) throws ExecutionException,
        InterruptedException {
        // main线程，主线程

        // 创建线程实现类对象
        MyThread thread = new MyThread();
        MyThread thread2 = new MyThread();

        // 创建执行服务，参数是线程池线程数量
        ExecutorService ser = Executors.newFixedThreadPool(2);
        // 提交执行
        Future<Boolean> res = ser.submit(thread);
        Future<Boolean> res2 = ser.submit(thread2);
        // 获取结果
        boolean r1 = res.get();
        boolean r2 = res2.get();
        // 关闭服务
        ser.shutdownNow();
    }
}
```



## 三、线程常用方法

### 1、线程的状态

- 新建状态（NEW）：线程已创建，尚未调用start()方法启动之前。
- 运行状态（RUNNABLE）：线程对象被创建后，调用该对象的start()方法，并获取CPU权限进行执行。
- 阻塞状态（BLOCKED）：线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。
- 等待状态（WAITING ）：等待状态。正在等待另一个线程执行特定动作来唤醒该线程的状态。
- 超时等待状态（TIME_WAITING）：有明确结束时间的等待状态。
- 终止状态（TERMINATED ）：当线程结束完成之后就会变成此状态。

### 2、线程常用方法

```
1、 sleep(Long time)方法：
 - 让线程阻塞的指定的毫秒数。
 - 指定的时间到了后，线程进入就绪状态。
 - sleep可研模拟网络延时，倒计时等。
 - 每一个对象都有一个锁，sleep不会释放锁。
```

代码如下（示例）：

```
public class MyThread implements Runnable {

    @Override
    public void run() {
        // 模拟倒计时
        for (int i = 10; i >= 0; i--) {
            try {
                System.out.println(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
    }

}
```

```
2、yield()方法：
 - 提出申请释放CPU资源，至于能否成功释放取决于JVM决定。
 - 调用yield()方法后，线程仍然处于RUNNABLE状态，线程不会进入阻塞状态。
 - 调用yield()方法后，线程处于RUNNABLE状态，就保留了随时被调用的权利。
```

```
public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程结束执行");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"a");
        Thread thread2 = new Thread(myThread,"b");
        thread.start();
        thread2.start();
    }

}
```

执行结果：从结果1看，a释放CPU成功后，b就抢到了CPU执行权，接着b也释放CPU成功，a抢到了CPU执行权；从结果2看，a并没有成功释放CPU。

```
结果1：
a线程开始执行
b线程开始执行
a线程结束执行
b线程结束执行
结果2：
a线程开始执行
a线程结束执行
b线程开始执行
b线程结束执行
```

```
3、join()方法：
 - 将当前的线程挂起，当前线程阻塞，待其他的线程执行完毕，当前线程才能执行。
 - 可以把join()方法理解为插队，谁插到前面，谁先执行。
```

代码如下（示例）：

```
public class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "join()线程执行：" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"a");
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("主线程执行：" + i);
            if (i == 2) {
                thread.join(); //主线程阻塞，等待thread一口气执行完，主线程才能继续执行
            }
        }
    }

}
```

```
4、setPriority (int newPriority)、getPriority()
- 改变、获取线程的优先级。
- Java提供一个线程调度器来监控程序中启动后进入就绪状态的所有线程，线程调度器按照优先级决定应该调度哪个线程来执行。
- 线程的优先级用数据表示，范围1~10。
- 线程的优先级高只是表示他的权重大，获取CPU执行权的几率大。
- 先设置线程的优先级，在执行start()方法。
```

代码如下（示例）：

```
public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程优先级："
            + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"a");
        Thread thread2 = new Thread(myThread,"b");
        Thread thread3= new Thread(myThread,"c");
        Thread thread4= new Thread(myThread,"d");
        thread3.setPriority(Thread.MAX_PRIORITY);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread4.setPriority(8);
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
```

执行结果：优先级高的线程不一定先执行

```
5、stop()、destroy()。【已废弃】
- JDK提供的上述两种方法已废弃，不推荐使用。
- 推荐线程自动停止下来，建议使用一个标识位变量进行终止，当flag=false时，则终止线程运行。
```

代码如下（示例）：

```
public class MyThread implements Runnable {
    /**
     * 标识位，为false时，线程结束
     */
    private boolean flag = true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    int i = 0;
    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "线程：" + i ++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"a");
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("主线程：" + i);
            if (i == 5) {
                // 当主线程 i== 5时，标识位变为false，控制子线程停止
                myThread.setFlag(false); 
            }
        }
    }
}
```

执行结果：主线程 i== 5 之后，子线程就停止运行了



## 四、多线程

**线程同步**就是**线程排队**，就是操作**共享资源**要有**先后顺序**，一个线程操作完之后，另一个线程才能操作或者读取。

- 防止线程同步访问***共享资源***造成冲突。
- ***变量***需要同步，***常量***不需要同步（常量存放于方法区）。
- 多个线程访问共享资源的代码（即线程执行体）有可能是同一份代码，也有可能是不同的代码；无论是否执行同一份代码，只要这些线程的代码访问同一份可变的共享资源，这些线程之间就需要同步。

### 1、守护（Deamon）线程

- 线程分为**用户线程**和**守护线程**。
- 虚拟机必须确保用户线程执行完毕。
- 虚拟机不用等待守护线程执行完毕。（如：后天记录操作日志、监控内存、垃圾回收等线程）。
- Thread.setDeamon(booean on)方法，true：守护线程；fasle：用户进程。默认是false。

代码如下（示例）：

```java
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        DeamonThread deamon = new DeamonThread();
        UserThread user = new UserThread();

        Thread deamonThread = new Thread(deamon);
        deamonThread.setDaemon(true); // 设置为守护进程
        deamonThread.start();

        Thread userThread = new Thread(user);
        userThread.start();
    }
}

// 模拟守护线程
class DeamonThread implements Runnable{

    @Override
    public void run() {
        // 验证虚拟机不用等待守护线程执行完毕，只要用户线程执行完毕，程序就结束。
        // 如果成功，则下面的打印不会一直输出；如果不成功，则下面的打印会一直输出
        while (true) {
            System.out.println("我是守护线程");
        }
    }
}

// 用户线程
class UserThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("我是用户线程 :" + i);
        }
    }
}
```

### 2、多线程并发与同步

**(1)、多线程并发**

在多线程场景下，如果多个线程修改***同一个资源***，或者一个线程修改共享资源，另一个线程读取共享资源，可能会导致结果不对的问题，这就导致线程不安全，即**并发**。导致线程并发的原因：

- 原子性：一个或多个操作在CPU执行过程中被中断。即一个操作或者多个操作要么全部执行并且执行的过程中不会被任何因素打断，要么就不执行。原子性就像数据库里面的事务一样，他们是一个整体，同存亡。
- 可见性：一个线程对共享变量的修改，另一个线程不能立马看到。
- 有序性：程序执行的顺序没有按照代码的先后顺序执行。

**下面以两个例子演示线程不安全问题。**

**示例1**：买票问题

代码如下（示例）

```
// 模拟线程不安全问示例1：买票
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        BuyTicker ticker = new BuyTicker();

        Thread person1Thread = new Thread(ticker, "person1");
        Thread person2Thread = new Thread(ticker, "person2");
        Thread person3Thread = new Thread(ticker, "person3");
        person1Thread.start();
        person2Thread.start();
        person3Thread.start();
    }
}

class BuyTicker implements Runnable{
    // 车票
    private int tickerNum = 10;
    // 停止线程标识
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buyTicker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buyTicker() throws InterruptedException {
        // 判断是否还有票
        if (tickerNum <= 0) {
            flag = false;
            return;
        }
        // 模拟延时
        Thread.sleep(100);
        // 买票
        System.out.println(Thread.currentThread().getName() + "买到第" + tickerNum -- + "张票");
    }
}
```

**示例2**：银行取钱

代码如下（示例）：

```
// 模拟线程不安全示例2：银行取钱
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000, "旅游基金");

        new Bank(account, 500, "你").start();
        new Bank(account, 600, "女朋友").start();
    }
}

// 账户
class Account {
    // 账户总余额
    int money;
    // 账户名
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行
class Bank extends Thread{
    // 客户账户
    Account account;
    // 取得钱数
    int drawingMoney;

    public Bank(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if (account.money- drawingMoney <= 0) {
            System.out.println(account.name+ "钱不够了，取不了了");
            return;
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 卡内余额 = 余额 - 取得钱
        account.money = account.money - drawingMoney;

        System.out.println(Thread.currentThread().getName()  + "取了" + drawingMoney);
        System.out.println(account.name + "余额为：" + account.money);

    }
}
```

执行结果：当你取500时，线程执行到account.money = account.money - drawingMoney之前，另一个线程抢到了CPU执行权，也执行到account.money = account.money - drawingMoney之前，现在余额还是1000，继续执行1000-500-900=-400.
**(2)、同步（解决并发问题）**

解决线程并发问题的方法是**线程同步**，**线程同步**就是**让线程排队**，就是操作**共享资源**要有**先后顺序**，一个线程操作完之后，另一个线程才能操作或者读取。

- 防止线程同步访问***共享资源***造成冲突。
- ***变量***需要同步，***常量***不需要同步（常量存放于方法区）。
- 多个线程访问共享资源的代码（即线程执行体）有可能是同一份代码，也有可能是不同的代码；无论是否执行同一份代码，只要这些线程的代码访问同一份可变的共享资源，这些线程之间就需要同步。

解决并发问题的两种方法：

① 同步方法：public synchronized void method(int args){执行体…}

synchronized 方法控制对(synchronized修饰的方法所在的对象，就是this)“对象”的访问，每个对象对应一把锁，每个synchronized方法都必须获得调用该方法的锁才能执行，否则线程会阻塞，synchronized所在方法一旦执行，就独占该锁，直到方法执行完
才释放锁，后面被阻塞的线程才能获得这个锁，继续执行。
缺陷：若将一个大的方法声明为synchronized 将会影响效率。需要修改的内容才需要锁，锁的太多，浪费资源。

② **同步代码块：synchronized (Obj){执行体…}**

- Obj称之为**同步监视器**，可以是任何对象，但是推荐使用**共享资源**作为同步监视器。
- 不同方法中无需指定同步监视器，因为同步方法中的同步监视器就是this，就是这个对象本身，或者是class。

**示例1**：买票问题（使用**同步方法**改造成线程安全）

代码如下（示例）：

```
// 模拟线程不安全问示例1：买票
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        BuyTicker ticker = new BuyTicker();

        Thread person1Thread = new Thread(ticker, "person1");
        Thread person2Thread = new Thread(ticker, "person2");
        Thread person3Thread = new Thread(ticker, "person3");
        person1Thread.start();
        person2Thread.start();
        person3Thread.start();
    }
}

class BuyTicker implements Runnable{
    // 车票
    private int tickerNum = 10;
    // 停止线程标识
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buyTicker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buyTicker() throws InterruptedException {
        // 判断是否还有票
        if (tickerNum <= 0) {
            flag = false;
            return;
        }
        // 模拟延时
        Thread.sleep(100);
        // 买票
        System.out.println(Thread.currentThread().getName() + "买到第" + tickerNum -- + "张票");
    }
}
```

**示例2**：银行取钱（使用**同步代码块**改造成线程安全）

代码如下（示例）：

```
// 模拟线程不安全示例2：银行取钱
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000, "旅游基金");

        new Bank(account, 500, "你").start();
        new Bank(account, 600, "女朋友").start();
    }
}

// 账户
class Account {
    // 账户总余额
    int money;
    // 账户名
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行
class Bank extends Thread{
    // 客户账户
    Account account;
    // 取得钱数
    int drawingMoney;

    public Bank(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.money- drawingMoney < 0) {
                System.out.println(account.name+ "钱不够了，取不了了");
                return;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 卡内余额 = 余额 - 取得钱
            account.money = account.money - drawingMoney;
            System.out.println(Thread.currentThread().getName()  + "取了" + drawingMoney);
            System.out.println(account.name + "余额为：" + account.money);
        }
    }
}
```

执行结果：你取了500后，你女朋友取600，就提示余额不足，不会出现余额为负数的情况了。这里的同步监视器是account，account才是操作的共享资源，而不是bank。

**示例3**：模拟集合ArrayList<>()是线程不安全的

代码如下（示例）：

```
import java.util.ArrayList;
import java.util.List;

// 模拟集合ArrayList<>()是线程不安全的
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
		// sleep可研模拟网络延迟
        Thread.sleep(5000);
        System.out.println(list.size());
    }
}
```

执行结果：list的大小应该是1000，结果是999

**使用同步代码块改造成线程安全的**

```
import java.util.ArrayList;
import java.util.List;

// 模拟线程不安全示例2：银行取钱
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                // 加锁
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }

        Thread.sleep(5000);
        System.out.println(list.size());
    }
}
```

执行结果：1000

### 3、死锁

（1）**死锁形成的原因**：多个线程各自占有一个资源，并且相互等待其他线程占有的资源才能运行，从而导致另个或者多个线程都在等待对方释放资源，都停止了执行。某一个同步代码块同时拥有“**两个以上对象的锁**”时，就可能会发生“死锁”的问题。

代码如下（示例）：

```
// 死锁例子：鱼和熊不可兼得
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        Person personA = new Person(0, "猎人A");
        Person personB = new Person(1, "猎人B");
        personA.start();
        personB.start();
    }
}

// 熊掌
class Bear {

}

// 鱼
class Fish {

}

// 人
class Person extends Thread {
    // 保证资源只有一份
    public static Bear bear = new Bear();
    public static Fish fish = new Fish();

    int choose;
    String personName;

    public Person (int choose, String personName) {
        this.choose = choose;
        this.personName = personName;
    }

    @Override
    public void run() {
        // 捕猎
        try {
            this.hunting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 捕猎方法
    private void hunting() throws InterruptedException {
        if (choose == 0) {
            synchronized (bear) {
                System.out.println(personName + "想捕捉熊");
                Thread.sleep(1000);
                synchronized (fish) {
                    System.out.println(personName + "想捕捉鱼");
                }
            }
        } else {
            synchronized (fish) {
                System.out.println(personName + "想捕捉鱼");
                Thread.sleep(1000);
                synchronized (bear) {
                    System.out.println(personName + "想捕捉熊");
                }
            }
        }
    }
}
```

执行结果：两个线程一直阻塞，都在等在对方释放锁，结果导致死锁。

（2）**解决死锁的方法**：同步代码块中不要相互嵌套，即，不要相互嵌套锁。

代码如下（示例）：

```
// 死锁例子：鱼和熊不可兼得
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        Person personA = new Person(0, "猎人A");
        Person personB = new Person(1, "猎人B");
        personA.start();
        personB.start();
    }
}

// 熊掌
class Bear {

}

// 鱼
class Fish {

}

// 人
class Person extends Thread {
    // 保证资源只有一份
    public static Bear bear = new Bear();
    public static Fish fish = new Fish();

    int choose;
    String personName;

    public Person (int choose, String personName) {
        this.choose = choose;
        this.personName = personName;
    }

    @Override
    public void run() {
        // 捕猎
        try {
            this.hunting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 捕猎方法
    private void hunting() throws InterruptedException {
        if (choose == 0) {
            synchronized (bear) {
                System.out.println(personName + "想捕捉熊");
                Thread.sleep(1000);
            }
            // 把嵌套的代码块拿到外面，两个代码块并列
            synchronized (fish) {
                System.out.println(personName + "想捕捉鱼");
            }
        } else {
            synchronized (fish) {
                System.out.println(personName + "想捕捉鱼");
                Thread.sleep(1000);
            }
            // 把嵌套的代码块拿到外面，两个代码块并列
            synchronized (bear) {
                System.out.println(personName + "想捕捉熊");
            }
        }
    }
}
```

执行结果：两个线程即捕到了熊，又捕到了鱼，解决了死锁问题。

### 4、Lock(锁)

**Lock 锁**也称**同步锁**，java.util.concurrent.locks.Lock 机制提供了⽐ synchronized 代码块和 synchronized ⽅法更⼴泛的锁定操作，同步代码块 / 同步⽅法具有的功能 Lock 都有，除此之外更强⼤，更体现⾯向对象。
创建对象 Lock lock = new ReentrantLock() ，加锁与释放锁⽅法如下：
public void lock() ：加同步锁
public void unlock() ：释放同步锁

**synchronized和Lock的对比：**

- Lock是显式锁（手动开启和关闭锁，别忘记关闭），synchronized是隐式锁，除了作用域就自动释放。
- Lock只是代码块锁（执行体放在开启锁和关闭锁中间），synchronized有代码块锁和方法锁。
- 使用Lock锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）。
- 优先使用顺序：Lock > 同步代码块（已经进入了方法体，分配了相应资源） > 同步方法（在方法体之外）。

代码如下（示例）：

```
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 测试 Lock锁
public class MyThread{
    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();
        new Thread(testLock,"a").start();
        new Thread(testLock,"b").start();
        new Thread(testLock,"c").start();
    }
}

class TestLock implements Runnable {

    // 车票
    private static int tickerNum = 10;

    // 创建一个Lock锁
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock(); // 加锁
            try {
                // 判断是否还有票
                if (tickerNum > 0) {
                    // 模拟延时
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 买票
                    System.out.println(Thread.currentThread().getName() + "线程买到第" + tickerNum -- + "张票");
                } else {
                    break;
                }
            } finally {
                lock.unlock(); // 解锁
            }
        }
    }
}
```

### 5、线程协作

- 线程之间需要进行通信，通信有**数据共享**（1、文件共享；2、网络共享；3、变量共享）和**线程协作**两种方式。
- **线程协作**指不同线程驱动的任务相互依赖，依赖一般就是对共享资源的依赖。（有共享就有竞争，有竞争就会有**线程安全**问题（即并发），解决并发问题就用线程同步）。

**应用场景**：生产者和消费者问题

- 假如仓库中只能存放一件商品，生产者将生产出来的产品放入仓库，消费者将仓库中产品取走消费。
- 如果仓库中没有产品，则生产者将产品放入仓库，否则停止生产并等待，直到仓库中的产品被消费者取走为止。
- 如果仓库中放有产品，则消费者可以将产品取走消费，否则停止消费并等待，直到仓库中再次放入产品为止。

**场景分析**：这是一个线程同步问题，生产者和消费者共享同一个资源，并且生产者和消费者之间相互依赖，互为条件。

- 对于生产者，没有生产产品之前，要通知消费者等待。而生产了产品之后，又需要马上通知消费者消费。
- 对于消费者，在消费之后，要通知生产者已经结束消费，需要生产新的产品以供消费。

在生产者消费者问题中，没生产出产品之前，消费者是不能消费的，反之，消费者没消费完之前，生产者是不能生产的。这就需要**锁**来实现线程之间的**同步**。仅有同步还不行，还要实现线程之间的消息传递，即**通信**。

Java提供了解决线程之间通信问题的方法：

| 方法名              | 作用                                                         |
| ------------------- | ------------------------------------------------------------ |
| wait ()             | 表示线程一直等待，直到其他线程通知，与 sleep 不同会释放锁    |
| wait (long timeOut) | 指定等待的毫秒数                                             |
| notify ()           | 唤醒一个处于等待状态的线程                                   |
| notifyAll()         | 唤醒同一个对象所有的调用 wait()方法的线程，优先级高的优先调度 |

**注意**：均是Object的方法，均只能在同步方法或者同步代码块中使用，否则会抛出异常IIIegalMonitorStageException。

**解决线程之间通信的方式**：**管程法**和**信号灯法**

**管程法**：生产者把生产好的数据放入缓存区，消费者从缓存区中拿出数据。

代码如下（示例）：

```
// 线程通信：生产消费模式-管程法
public class MyThread{
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Consumer(container).start();
    }
}

// 产品
class Chicken {
    int id;

    public Chicken (int id) {
        this.id = id;
    }
}

// 生产者
class Productor extends Thread {
    SynContainer synContainer;

    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            synContainer.pushTo(new Chicken(i));
        }
    }
}

// 消费者
class Consumer extends Thread {
    SynContainer synContainer;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            synContainer.popTo();
        }
    }
}

// 容器
class SynContainer {
    // 定义一个大小为10的容器
    Chicken[] chickens = new Chicken[10];
    // 容器计数器
    int count;

    // 生产者生产产品方法
    public synchronized void pushTo(Chicken chicken) {
        // 如果容器满了，就停止生产
        if (chickens.length == count) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果容器没满，就往容器中放入产品
        chickens[count] = chicken;
        System.out.println("生产了" + chicken.id + "个鸡腿");
        count ++;

        // 通知消费者消费
        this.notifyAll();
    }

    // 消费者消费产品方法
    public synchronized Chicken popTo() {
        // 如果容器中没有产品了，就停止消费
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 如果容器有产品，就可以消费
        count --;
        Chicken chicken = chickens[count];
        System.out.println("消费了第" + chicken.id + "个鸡退");

        //只要消费了，就通知生产者生产
        this.notifyAll();
        return chicken;
    }
}
```

**信号灯法**：设置一个标识位，标识位来判断线程是等待还是执行。

代码如下（示例）：

```
// 线程通信：生产消费模式-信号灯法
public class MyThread{
    public static void main(String[] args) {
        CCTV cctv = new CCTV();
        new Player(cctv).start();
        new Watcher(cctv).start();
    }
}

// 演员
class Player extends Thread {
    CCTV cctv;

    public Player(CCTV cctv) {
        this.cctv = cctv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i%2 == 0) {
                cctv.play("快乐大本营");
            } else {
                cctv.play("天天向上");
            }
        }
    }
}

// 观众
class Watcher extends Thread {
    CCTV cctv;

    public Watcher(CCTV cctv) {
        this.cctv = cctv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            cctv.watch();
        }
    }
}

// 电视
class CCTV {
    // 表演的节目
    String voice;
    // 标识
    boolean flag = true;

    // 表演节目
    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.voice = voice;
        System.out.println("演员表演了：" + voice);
        // 通知观众观看
        this.notifyAll();
        this.flag = !flag;
    }

    // 观看节目
    public synchronized void watch () {
        if (flag) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 如果容器有产品，就可以消费
        System.out.println("观众观看了：" + voice);
        // 通知演员表演节目
        this.notifyAll();
        this.flag = !flag;
    }
}
```

### 6、线程池

背景：经常创建和销毁线程，消耗特别大的资源，比如并发的情况下的线程，对性能影响很大。**线程池**就是问题为了解决这个问题，提前创建好多个线程，放在线程池中，使用时直接获取，使用完放回线程池中，可以避免频繁的创建、销毁，实现重复利用。

**优点**：

- 提高相应速度（减少创建线程的时间）
- 降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
- 便于线程管理：corePoolSize：核心池的大小。maximumPoolSize：最大线程数。
  keepAliveTime：线程没有任务时最多保持多长时间后终止。

线程池相关的API：

- ExecutorService：线程池接口。

- 常见的实现类：ThreadPoolExecutor。
  void execute(Runnable command)：执行任务命令，没有返回值，一般用来执行Runnable.
  <T> Future<T> submit(Callable<T> task)：执行任务，有返回值，一般用来执行Callable
- void shutdown()：关闭连接池

- Executors：工具类，线程池的工厂类，用来创建并返回不同类型的线程池

代码如下（示例）：

```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 线程池
public class ThreadPool {
    public static void main(String[] args) {
        // 1、创建服务，创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        MyThread myThread = new MyThread();
        // 执行
        service.execute(myThread);
        service.execute(myThread);
        service.execute(myThread);
        service.execute(myThread);
        // 关闭连接
        service.shutdown();
    }
}

// 演员
class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
```

