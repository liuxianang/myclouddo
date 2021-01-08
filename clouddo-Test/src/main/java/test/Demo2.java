package test;

import static org.apache.ibatis.ognl.OgnlOps.less;

public class Demo2 {
    public static void main(String[] args){
        System.out.println(gcd(133,333322));
        System.out.println(Math.abs(-2147483648));
        System.out.println( 1.0/0.0);
        if(1==1)   System.out.println("1");  if(2==1)System.out.println("2");else System.out.println("");
        System.out.println( ( 0 + 15 ) / 2);
        System.out.println( 2.0e-6 * 100000000.1);
        System.out.println(  true && false || true && true);
        System.out.println(   (1 + 2.236)/2);
        System.out.println(1 + 2 + 3 + 4.0);
        System.out.println( 4.1 >= 4);
        System.out.println(  1 + 2 + "3");
        System.out.println(  1 + 2 *3);
      /*  int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++)
        {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }*/
        double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001)
            t = (9.0/t + t) / 2.0;
        System.out.printf("%.5f\n", t);
        int sum = 0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < i; j++)
                sum++;
        System.out.println(sum);
        int sum1 = 0;
        for (int i = 1; i < 1000; i *= 2)
            for (int j = 0; j < 1000; j++)
                sum1++;
        System.out.println(sum1);
        System.out.println('b');
         System.out.println('b' + 'c');
        System.out.println((char) ('a' + 4));
        String s = "";
        for (int n = 100; n > 0; n /= 2)
            s = (n % 2) + s;
        System.out.println(s);
        int[] a=new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = i * i;
        System.out.println(a[9]);
        String ss = null;
        System.out.println(ss);
        int bb = 0;
        System.out.println(bb);
        int[] cc = new int[10];
        for (int i = 0; i < 10; i++)
            cc[i] = 9 - i;
        for (int i = 0; i < 10; i++)
            cc[i] = a[a[i]];
        for (int i = 0; i < 10; i++)
            System.out.println(i);
    }
    /*欧几里得算法*/
    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);}



}