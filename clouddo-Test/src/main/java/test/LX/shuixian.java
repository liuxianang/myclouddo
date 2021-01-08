package test.LX;

public class shuixian {
    static int b, bb, bbb;

    public static void main(String[] args) {

        for (int num = 101; num < 1000; num++) {
            shuixian tnn = new shuixian();
            tnn.f(num);
        }
    }

    public void f(int m) {
        bbb = m / 100;
        bb = (m % 100) / 10;
        b = (m % 100) % 10;
        if ((bbb * bbb * bbb + bb * bb * bb + b * b * b) == m) {
            System.out.println(m);
        }
    }
}
