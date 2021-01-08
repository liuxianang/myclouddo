package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Path;

import static java.nio.file.Files.newBufferedReader;

public class IOTest {
    public static void main(String[] args) throws Exception {
        String str = "中国人";           /*FileOutputStream fos  = new FileOutputStream("1.txt");                     fos.write(str.getBytes("UTF-8"));           fos.close();*/                     /*FileWriter fw = newFileWriter("1.txt");           fw.write(str);           fw.close();*/
        PrintWriter pw = new PrintWriter("1.txt", "utf-8");
        pw.write(str);
        pw.close();                     /*FileReader fr = newFileReader("1.txt");           char[] buf = new char[1024];           int len = fr.read(buf);           String myStr = new String(buf,0,len);           System.out.println(myStr);*/           /*FileInputStream fr = new FileInputStream("1.txt");           byte[] buf = new byte[1024];           int len = fr.read(buf);           String myStr = newString(buf,0,len,"UTF-8");
更多干货，敬请关注“养码场”微信公众号
          System.out.println(myStr);*/
        BufferedReader br = newBufferedReader((Path) new InputStreamReader(new FileInputStream("C：\\1.txt"), "UTF-8"));
        String myStr = br.readLine();
        br.close();
        System.out.println(myStr);
    }

}