package syn;

public class SynObject {

    public synchronized  void doSomething(){
        //do something
        System.out.println(">>>>>>>>");
    }

    public static void main(String[] args) throws InterruptedException {
        SynObject synObject= new SynObject();
        synchronized (synObject){
            while (true){
                //loop forever
                Thread.sleep(10000);
            }
        }

    }}