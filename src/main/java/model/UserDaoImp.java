package model;

/**
 * Created by Administrator on 2017-02-27.
 */

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDaoImp implements UserDao {
    private JdbcTemplate jdbcTemplate;

    /*
     * 查询所有的数据信息
     * (non-Javadoc)
     * @see UserDAOInterface#find()
     */
    public List find(){
        String sql="select * from t_user";
        return jdbcTemplate.query(sql, new UserMapper());
    }
    /*
     *
     * 使用rowMapper 19行用到， 因为query方法不能直接放回一个数组，所以我们只能通过rowMapper赋值给uservo;
     *
     * RowMapper可以将数据中的每一行封装成用户定义的类，在数据库查询中，如果返回的类型是用户自定义的类型则需要包装
     */
    private static final class UserMapper implements RowMapper {


        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            UserVO uservo=new UserVO();
            uservo.setUserId(rs.getInt("UserId"));
            uservo.setUserName(rs.getString("UserName"));
            uservo.setPassword(rs.getString("Password"));
            return uservo;
        }


    }
    /*
     * 删除信息
     * (non-Javadoc)
     * @see UserDAOInterface#delete(int)
     */
    public void delete(int UserId) {
        // TODO Auto-generated method stub
        String sql="delete from t_user where UserId=?";
        jdbcTemplate.update(sql,UserId);
    }
    /*
     * 增加信息
     * (non-Javadoc)
     * @see UserDAOInterface#insert(UserVO)
     */
    public void insert(UserVO uservo) {
        // TODO Auto-generated method stub
        String sql=" insert into t_user (name,Password) values(?,?)";
        jdbcTemplate.update(sql,new Object[]{
                uservo.getUserName(),uservo.getPassword()
        });
    }
    /*
     * 查询信息
     * (non-Javadoc)
     * @see UserDAOInterface#select(int)
     */
    public List<UserVO> select(String UserId) {
        // TODO Auto-generated method stub
        String sql="select * from t_user where UserId='"+UserId+"'";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserVO.class));
        }catch(EmptyResultDataAccessException e){
            return null;
        }

    }
    public List<UserVO> select() {
        // TODO Auto-generated method stub
        String sql="select * from t_user ";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserVO.class));
        }catch(EmptyResultDataAccessException e){
            return null;
        }

    }
    public UserVO selectByName(String UserName, String Password){
        // TODO Auto-generated method stub
        String sql="select * from t_user where UserName='"+UserName+"' and Password ='"+Password+"'";
        try{
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(UserVO.class));
        }catch(EmptyResultDataAccessException e){
            return null;
        }

    }
    /*
     * 更新信息
     * (non-Javadoc)
     * @see UserDAOInterface#update(UserVO)
     */
    public void update(UserVO uservo) {
        // TODO Auto-generated method stub
        String sql="update t_user set UserName=?,Password=? where UserId=?";
        jdbcTemplate.update(sql,uservo.getUserName(),uservo.getPassword(),uservo.getUserId());
    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}