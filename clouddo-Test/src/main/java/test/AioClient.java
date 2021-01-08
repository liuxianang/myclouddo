package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class AioClient extends Thread{

    @Override
    public void run() {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("127.0.0.1", 9090));
            // 先写后读
            sc.write(ByteBuffer.wrap("client request".getBytes()));

            ByteBuffer buf = ByteBuffer.allocate(1024);
            sc.read(buf);
            buf.flip();
            byte[] temp = new byte[buf.remaining()];
            buf.get(temp);
            System.out.println("client get:" + new String(temp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(new File(
                "d:\\temp_buffer.tmp"));
        FileChannel fc = fin.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fc.read(byteBuffer);
        fc.close();
        byteBuffer.flip();//读写转换
        new AioClient().start();
        new AioClient().start();
        new AioClient().start();
    }
}

