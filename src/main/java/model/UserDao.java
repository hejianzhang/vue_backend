package model;
import java.util.List;


public interface UserDao {
    public void insert(UserVO uservo);
    public void delete(int UserId);
    public void update(UserVO uservo);
    public List<UserVO> select(String UserId);
    public List<UserVO> select();
    public List find();
    public UserVO selectByName(String UserName, String Password);
}
