package 多线程;

public class Singleton {

    private volatile static Singleton instance = null;
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(instance == null){ // 第①处
            synchronized (Singleton.class) {
                if(instance == null){  // 第②处
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
