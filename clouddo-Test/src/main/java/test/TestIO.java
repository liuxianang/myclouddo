package test;

import java.io.*;

public class TestIO {
    public static void printStream() throws FileNotFoundException, IOException {
        try (
                FileOutputStream fos = new FileOutputStream("tmp.txt");
                PrintStream ps = new PrintStream(fos)) {
            ps.println("普通字符串\n");
            //输出对象
            ps.println(new TestIO());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输出完成");

    }
    public static void stringNode() throws IOException {
        String str = "天王盖地虎\n"
                + "宝塔镇河妖\n";
        char[] buf = new char[32];
        int hasRead = 0;
        //StringReader将以String字符串为节点读取数据
        try (StringReader sr = new StringReader(str)) {
            while ((hasRead = sr.read(buf)) > 0) {
                System.out.print(new String(buf, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //由于String是一个不可变类，因此创建StringWriter时，实际上是以一个StringBuffer作为输出节点
        try (StringWriter sw = new StringWriter()) {
            sw.write("黑夜给了我黑色的眼睛\n");
            sw.write("我却用它寻找光明\n");
            //toString()返回sw节点内的数据
            System.out.println(sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void keyIn() throws IOException {
        try (
                //InputStreamReader是从byte转成char的桥梁
                InputStreamReader reader = new InputStreamReader(System.in);
                //BufferedReader(Reader in)是char类型输入的包装类
                BufferedReader br = new BufferedReader(reader);
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equals("exit")) {
                    //System.exit(1);
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pushback() throws FileNotFoundException, IOException {
        try (PushbackReader pr = new PushbackReader(new FileReader("tmp.txt"),64)) {
            char[] buf = new char[32];
            String lastContent = "";
            int hasRead = 0;
            while ((hasRead = pr.read(buf)) > 0) {
                String content = new String(buf, 0, hasRead);
                int targetIndex = 0;
                if ((targetIndex = (lastContent + content).indexOf("targetIndex = (lastContent + content)")) > 0) {
                    pr.unread((lastContent + content).toCharArray());
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }
                    pr.read(buf , 0 , targetIndex);
                    System.out.println(new String(buf, 0 , targetIndex));
                    System.exit(0);
                } else {
                    System.out.println(lastContent);
                    lastContent = content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void FileInputStreamTest() throws IOException {
        FileInputStream fis = new FileInputStream("tmp2.txt");
        byte[] buf = new byte[1024];
        int hasRead = 0;

        //read()返回的是单个字节数据（字节数据可以直接专程int类型)，但是read(buf)返回的是读取到的字节数，真正的数据保存在buf中
        while ((hasRead = fis.read(buf)) > 0) {
            //每次最多将1024个字节转换成字符串，这里tmp2.txt中的字符小于1024，所以一次就读完了
            //循环次数 = 文件字符数 除以 buf长度
            System.out.println(new String(buf, 0 ,hasRead));
            /*
             * 将字节强制转换成字符后逐个输出，能实现和上面一样的效果。但是如果源文件是中文的话可能会乱码

            for (byte b : buf)    {
                char ch = (char)b;
                if (ch != '\r')
                System.out.print(ch);
            }
            */
        }
        //在finally块里close更安全
        fis.close();
    }

    public static void FileReaderTest() throws IOException {

        try (
                // 在try() 中打开的文件， JVM会自动关闭
                FileReader fr = new FileReader("tmp2.txt")) {
            char[] buf = new char[32];
            int hasRead = 0;
            // 每个char都占两个字节，每个字符或者汉字都是占2个字节，因此无论buf长度为多少，总是能读取中文字符长度的整数倍,不会乱码
            while ((hasRead = fr.read(buf)) > 0) {
                // 如果buf的长度大于文件每行的长度，就可以完整输出每行，否则会断行。
                // 循环次数 = 文件字符数 除以 buf长度
                System.out.println(new String(buf, 0, hasRead));
                // 跟上面效果一样
                // System.out.println(buf);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void FileOutputStreamTest() throws FileNotFoundException, IOException {
        try (
                //在try()中打开文件会在结尾自动关闭
                FileInputStream fis = new FileInputStream("tmp2.txt");
                FileOutputStream fos = new FileOutputStream("tmp3.txt");
        ) {
            byte[] buf = new byte[4];
            int hasRead = 0;
            while ((hasRead = fis.read(buf)) > 0) {
                //每读取一次就写一次，读多少就写多少
                fos.write(buf, 0, hasRead);
            }
            System.out.println("write success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileWriterTest() throws IOException {
        try (FileWriter fw = new FileWriter("tmp4.txt")) {
            fw.write("天王盖地虎2\r\n");
            fw.write("宝塔镇河妖2\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        //printStream();
        //FileInputStreamTest();
       //FileReaderTest();
        //FileOutputStreamTest();
        //FileWriterTest();
        //stringNode();
        //keyIn();
        pushback();
    }
}