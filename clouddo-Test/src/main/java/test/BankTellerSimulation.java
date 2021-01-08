package test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多线程模拟银行出纳员问题
 * */

//模拟顾客类，完全只是一个可读类，不需要同步
class Customer {
    //该顾客所需服务时间
    private final int serviceTime;

    public Customer(final int serviceTime) {
        this.serviceTime = serviceTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public String toString() {
        return "["+serviceTime+"]";
    }
}

//模拟顾客排队的队列，继承了阻塞队列
//是一个多线程共享对象，这个队列继承的是ArrayBlocingQueue
//是一个有最大长度的队列
class CustomerLine extends ArrayBlockingQueue<Customer> {

    //指定允许队列的最大长度
    public CustomerLine(int maxSize) {
        super(maxSize);
    }

    //重写toString()方法，用来进行显示当前排队中的顾客
    public String toString() {
        if(this.size()==0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for(Customer customer :this) {
            result.append(customer);
        }
        return result.toString();
    }
}

//顾客生产类
//间隔随机然后向队列中添加一位顾客的线程
class CustomerGenerator implements Runnable {
    private CustomerLine customerLine; //阻塞队列
    private static Random rand = new Random(47);
    public CustomerGenerator(CustomerLine customerLine) {
        this.customerLine = customerLine;
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                //线程睡眠随机时间以后，产生一个顾客对象,添加到队列中
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                //添加一个服务时间随机的顾客
                customerLine.add(new Customer(rand.nextInt(1000)));
            }
        } catch(InterruptedException ex) {
            System.out.println(this+" 通过中断异常退出");
        }
        System.out.println(this+" terminating");
    }
}

//出纳员类，负责对队列中的顾客进行服务
//注意其有两种状态：服务顾客或做一些其它的事情
class Teller implements Runnable,Comparable<Teller> {
    private static int counter = 0;
    private final int id = counter++;

    //该Teller服务的顾客队列
    private CustomerLine customerLine;
    private int customerServed = 0;//已服务的顾客数

    //标志目前是被分配到服务CustomerLine还是做一些其它事
    //默认是分配给customerLine
    private boolean servingCustomerLine=true;
    public Teller(CustomerLine cl) {
        this.customerLine = cl;
    }

    //正常情况下会从CustomerLine中取出一个Customer进行服务
    //如果被分配到做其它事，则会被挂起
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Customer customer = customerLine.take();

                //睡眠一段时间模拟服务Customer
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized(this) {
                    while(!servingCustomerLine) { //被分配做其它事情
                        wait();
                    }
                }
            }
        } catch(InterruptedException ex) {
            System.out.println(this+"通过中断异常退出");
        }
        System.out.println(this+"Terminating");
    }

    //调用这个方法意味着该Teller对象被分配去做其它事情
    public synchronized void doSomethingElse() {
        customerServed = 0;
        servingCustomerLine=false; //设定标志，是当前服务线程挂起
    }

    //被分配到服务到customerLine
    public synchronized void serveCustomerLine() {
        servingCustomerLine = true;
        notifyAll();//通知挂起线程
    }

    public String toString() {
        return "Teller "+id+" ";
    }
    public String shortString() {
        return "T "+id;
    }

    //按以服务顾客数确定Teller的优先级，给优先队列使用
    @Override
    public synchronized int compareTo(Teller other) {
        return customerServed < other.customerServed ? -1:
                (customerServed==other.customerServed ? 0 :1);
    }

}

//服务管理和调度Teller的类
//这个TellerManager类是各种活动的中心，它跟踪所有的出纳员以及等待服务的顾客
//从adjustTellerNumber()中可以看到，它会根据实际情况调整服务CustomerLine的
//Teller数量，以期达到最优出纳员的数目。
class TellerManager implements Runnable {
    private ExecutorService exec;  //负责启动Teller线程
    private CustomerLine customerLine;

    //按服务顾客数由少到多优先的优先队列，用来进行调度
    //每次都取出服务顾客数最少的出纳员来进行服务，以保证公平性。
    private PriorityQueue<Teller> workingTellers
            = new PriorityQueue<>();

    //正在做其它事情的Teller队列
    private Queue<Teller> tellersDoingOtherThings
            = new LinkedList<Teller>();

    private int adjustmentPeriod; //调度时间

    private static Random rand = new Random();

    public TellerManager(ExecutorService exec,CustomerLine
            customerLine,int adjustmentPeriod) {
        this.exec =exec;
        this.customerLine = customerLine;
        this.adjustmentPeriod = adjustmentPeriod;

        //在构造器中先分配一个Teller进行服务
        Teller teller = new Teller(customerLine);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    //通过当前customerLine中的顾客数以及正在工作的Teller
    //人数的比例关系，来确定是否要加/减Teller的数目
    public void adjustTellerNumber() {

        //如果customerLine队列过长，则增加服务的Teller
        if(customerLine.size()/workingTellers.size()>2) {

            //如果在做其它事的Teller则从中抽调出人来,否则重新分配一个Teller
            if(tellersDoingOtherThings.size()>0) {
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.add(teller);
                return;
            }
            //重新分配一个Teller
            Teller teller = new Teller(customerLine);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }

        //当前Tellers过多时，抽调一些去做其它工作
        if(workingTellers.size()>1&&customerLine.size()/workingTellers.size()<2) {

            reassignOneTeller();

            //如果这里只有没有customer需要服务，则只需留下一个Teller
            if(customerLine.size()==0) {
                while(workingTellers.size()>1) {
                    reassignOneTeller();
                }
            }
        }
    }

    private void reassignOneTeller() {
        //从工作队列中取出一个Teller来
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();//让他去做其它工作
        tellersDoingOtherThings.offer(teller);
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);

                //按当前情况进行动态调整
                adjustTellerNumber();

                //打印当前的customerLine和workingTeller的情况
                //从结果可以看到随着customerLine大小的变化，workingTeller
                //的人数也是不断变化的。
                System.out.print(customerLine+"{");
                for(Teller teller: workingTellers) {
                    System.out.print(teller.shortString()+" ");
                }
                System.out.println("}");
            }
        } catch(InterruptedException ex) {
            System.out.println(this+"通过中断异常退出");
        }
        System.out.println(this+"terminating");
    }

    public String toString() {
        return "TellerManager";
    }
}


public class BankTellerSimulation {
    static final int SIZE = 50;//顾客队列的最大长度
    static final int PERIOD = 1000;//调整时间间隔
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customerLine = new CustomerLine(SIZE);
        exec.execute(new CustomerGenerator(customerLine));
        exec.execute(new TellerManager(exec,customerLine,PERIOD));
        System.out.println("Press 'Enter' to exit");
        System.in.read();
        exec.shutdownNow();
    }
}/*Output
Press 'Enter' to exit
[200][207]{T 0 T 1 }
[861][258][140][322]{T 1 }
[258][140][322][383][575][342][804][826][896][984]{T 1 T 0 }
[804][826][896][984][810][141][12][689][992][976][368][395][354]{T 1 T 0 T 2 }
[141][12][689][992][976][368][395][354][222][687][634][317][242][698]{T 1 T 0 T
2 T 3 }
[354][222][687][634][317][242][698][899][665][909][209][158][273][894]{T 1 T 0 T
 2 T 3 T 4 }
[158][273][894][984][533][8][328][779][882][37]{T 1 T 0 T 2 T 3 T 4 }
[871][17][828][696][994][419][738][434][106][718][965][416][217][677]{T 1 T 0 T
2 T 3 T 4 }
Teller 0 通过中断异常退出
Teller 3 通过中断异常退出
Teller 3 Terminating
Teller 1 通过中断异常退出
Teller 1 Terminating
CustomerGenerator@10361085 通过中断异常退出
Teller 2 通过中断异常退出
TellerManager通过中断异常退出
Teller 4 通过中断异常退出
TellerManagerterminating
Teller 2 Terminating
CustomerGenerator@10361085 terminating
Teller 0 Terminating
Teller 4 Terminating
*///:~