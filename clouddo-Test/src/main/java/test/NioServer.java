package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioServer {
    private int num = 0;
    private Map<SocketChannel, Integer> map = new HashMap<SocketChannel, Integer>();
    private ByteBuffer buf = ByteBuffer.allocate(1024);
    private Selector selector;
    public NioServer(int port) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            this.selector = Selector.open();
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start, port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            try {
                this.selector.select();
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) accept(key);
                        if (key.isReadable()) read(key);
                        if (key.isWritable()) write(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            this.num ++;
            this.map.put(sc, this.num);
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            this.buf.clear();
            SocketChannel sc = (SocketChannel) key.channel();
            sc.read(this.buf);
            this.buf.flip();
            byte[] temp = new byte[this.buf.remaining()];
            buf.get(temp);
            System.out.println("server get:" + new String(temp));

            key.interestOps(SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            int num = this.map.get(sc);
            sc.write(ByteBuffer.wrap(("你是第" + num + "个链接者").getBytes()));

            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NioServer(9090).listen();
    }
}
