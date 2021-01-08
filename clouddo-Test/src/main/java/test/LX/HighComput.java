package test.LX;

public class HighComput {
    static double height = 100;
    static double distance = 100;

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            distance = distance + height;
            height = height / 2;
        }

        System.out.println("路程：" + distance);
        System.out.println("高度：" + height / 2);
    }
}
