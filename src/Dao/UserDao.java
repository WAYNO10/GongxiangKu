package Dao;


import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static final String URL = "jdbc:derby://localhost:1527/mydatabase;create=true";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "123";

    private Connection getConnection() throws SQLException {

        Connection conn =null ;
        try {
            //创建数据库连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(URL + " connected...");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return conn;
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return 是否成功插入
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO \"user\" (userName, password, userRole) " +
                "VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUserRole());

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除用户
     *
     * @param userName 用户名
     * @return 是否成功删除
     */
    public boolean deleteUser(String userName) {
        String sql = "DELETE FROM \"user\" WHERE userName = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return 是否成功更新
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE \"user\" SET " +
                "password = ?, " +
                "userRole = ? " +
                "WHERE userName = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getUserRole());
            pstmt.setString(3, user.getUserName());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询用户
     *
     * @param userName 用户名
     * @return 用户对象列表
     */
    public List<User> findUser(String userName) {
        String sql = "SELECT * FROM \"user\" WHERE userName = ?";
        List<User> users = new ArrayList<>();
        try  {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getString("userRole"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象，如果没有找到则返回 null
     */
    public User findUserById(int userId) {
        String sql = "SELECT * FROM \"user\" WHERE userId = ?";
        User user = null;
        try  {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getString("userRole"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 查询所有用户
     *
     * @return 用户对象列表
     */
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM \"user\"";
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserRole(rs.getString("userRole"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}
