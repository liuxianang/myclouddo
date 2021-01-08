package test;

public class Chihuo extends Thread {
    private Baozi bz;
    public Chihuo(Baozi bz){
        this.bz = bz;
    }

    @Override
    public void run() {//设置线程任务 吃包子
        while (true){//吃货一直吃包子
            synchronized (bz){
                if (bz.flag == 0){//如果没有包子
                    try{
                        bz.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("吃货正在吃" + bz.pi + bz.xian + "的包子");
                bz.flag = bz.flag-1;//吃货吃完包子，修改包子的状态为 false
                System.out.println("吃货已经把" + bz.pi + bz.xian + "的包子吃完，包子铺开始生产包子");
                bz.notify();//唤醒包子铺线程生产包子
                System.out.println("————————————————————————————————");
            }
        }
    }
}
