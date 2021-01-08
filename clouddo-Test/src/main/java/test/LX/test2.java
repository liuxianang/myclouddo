package test.LX;
//判断101-200之间有多少个素数，并输出所有素数。
public class test2 {
    public static int count = 0;

    public static void main(String[] args) {
        for (int i = 101; i < 200; i++) {
            boolean b = true;// 默认此数就素数
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    b = false; // 此数不是素数
                    break;
                }
            }
            if (b) {
                count++;
                System.out.print(i + " ");
            }
        }
        System.out.println("\n素数的个数：" + count);
    }

}
