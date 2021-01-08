package 多线程;





public class UnsafeTest01 {

    public static void main(String[] args) {
        //一份资源
        Web12306 web = new Web12306();
        System.out.println(Thread.currentThread().getName());

        //三个代理
        new Thread(web,"码畜").start(); //加上名字，区分线程
        new Thread(web,"码农").start();
        new Thread(web,"码蟥").start();
    }
}

class Web12306 implements Runnable{
    private int ticketNums = 10; //99张票
    private boolean flag = true;
    @Override
    public void run() {
        while(flag){
            test();
        }

    }
    public void test(){
        if(ticketNums<0){
            flag= false;
            return;
        }
        try {
            Thread.sleep(200);//模拟延时
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Thread.currentThread().getName()谁运行run就是代表谁
        System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);

    }
}

