package test.LX;
//1 斐波那契数列
public class test1 {
    public static final int MONTH = 15;
    public static void main(String[] args) {
        long f1 = 1L, f2 = 1L;
        long f;
        for (int i = 3; i < MONTH; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.print("第" + i + "个月的兔子对数: ");
            System.out.println(" " + f2);
        }

    }
}
