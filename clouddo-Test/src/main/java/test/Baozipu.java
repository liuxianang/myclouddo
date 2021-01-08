package test;
public class Baozipu extends Thread {
    private Baozi bz;//创建包子变量

    public Baozipu(Baozi bz){
        this.bz = bz;
    }

    @Override
    public void run() {//设置线程任务，生产包子
        int count = 0;
        while (true){//让包子铺一直生产包子
            synchronized (bz){
                if (bz.flag>0){//如果有包子
                    try{
                        System.out.println(">>>>>>>>>>>>>"+bz.flag);
                        bz.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if (count % 2 == 0){
                    bz.pi = "薄皮";
                    bz.xian = "猪肉";
                }else {
                    bz.pi = "冰皮";
                    bz.xian = "韭菜";
                }
                count ++;
                System.out.println("包子铺正在生产" + bz.pi + bz.xian + "包子");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                bz.flag = bz.flag+1;//包子铺生产好了包子，修改包子的状态为 有
                System.out.println("包子铺已经生产好了" + bz.pi + bz.xian + "的包子，吃货们可以进来吃了");
                bz.notify();//并唤醒吃货
            }
        }
    }
}
