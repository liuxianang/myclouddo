package test.LX;

import java.util.Scanner;

public class DiGui {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        DiGui tfr = new DiGui();
        System.out.println(tfr.recursion(n));
    }

    public long recursion(int n) {
        long value = 0;
        if (n == 1 || n == 0) {
            value = 1;
        } else if (n > 1) {
            value = n * recursion(n - 1);
        }
        return value;
    }
}
