package ioc;

public class UserServiceImpl implements UserService {
    private     IUserDao  userDao;    //set方法
    public void  setUserDao(IUserDao  userDao) {
        this.userDao = userDao;
    }
    @Override
    public void addUser(String username,String password) {
        userDao.InsertUser(username,password);
    }
}