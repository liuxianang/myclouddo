package test.LX;

public class WanQuan {
    public static void main(String[] args) {
        for (long l = 1L; l < 100000; l++) {
            if (Math.sqrt((long) (l + 100)) % 1 == 0) {
                if (Math.sqrt((long) (l + 268)) % 1 == 0) {
                    System.out.println(l + "加100是一个完全平方数，再加168又是一个完全平方数");
                }
            }
        }
    }
}

