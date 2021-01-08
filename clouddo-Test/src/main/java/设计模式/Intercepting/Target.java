package 设计模式.Intercepting;

public class Target {
    public void execute(String request){
        System.out.println("Executing request: " + request);
    }
}
