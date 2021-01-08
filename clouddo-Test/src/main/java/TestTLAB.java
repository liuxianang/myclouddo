import java.time.Instant;
/**
 * 栈上分配，依赖于逃逸分析和标量替换
 *
 * @author Sven Augustus
 */
public class TestTLAB {
    // private static User u;
    /**
     * 一个User对象的大小：markdown 8 + class pointer 4 + int 4 + string (oops) 4 + padding 4 = 24B <br> 如果分配 100_000_000 个，则需要
     * 2400_000_000 字节， 约 2.24 GB。
     */
    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private static void alloc() {
        User u = new User(1, "SvenAugustus");
        // u = new User(1, "SvenAugustus");
    }
    public static void main(String[] args) throws InterruptedException {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 100_000_000; i++) {
            alloc();
        }
        System.out.println(Instant.now().toEpochMilli() - start);
    }
}