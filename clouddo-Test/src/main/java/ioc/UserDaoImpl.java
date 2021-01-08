package ioc;

public class UserDaoImpl implements IUserDao {
    @Override
    public void InsertUser(String username, String password) {
        System.out.println("----UserDaoImpl --addUser----");
    }
}
