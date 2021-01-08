package test.LX;

import java.util.Scanner;

public class ChaXun {
    static int grade;

    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        int s = str.nextInt();
        ChaXun fc = new ChaXun();
        grade = fc.compare(s);
        if (grade == 1) {
            System.out.print('A');
        } else if (grade == 2) {
            System.out.print('B');
        } else {
            System.out.println('C');
        }
    }

    public int compare(int s) {
        return s > 90 ? 1 : s > 60 ? 2 : 3;
    }
}