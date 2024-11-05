/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
/**
 *
 * @author 王陈宇科
 */
public class ConnectDBTest {
    public static Connection conn;

    public static final String URL = "jdbc:derby:mydatabase;create=true";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "123";
    

    public static Connection connectDB() throws SQLException {
        try {
            //创建数据库连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(URL + " connected...");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return conn;
    }
    
    public static void createDBTable(Connection conn){
        Statement statement = null;
        try {

            //statement对象
            statement = conn.createStatement();
            // 创建用户表
//            String createUsersTable = "CREATE TABLE article_category\n" +
//"(\n" +
//"    id  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
//"    article_id  INT NOT NULL,\n" +
//"    category_id INT NOT NULL,\n" +
//"    UNIQUE (article_id , category_id)\n" +
//")";
           
            
//            CREATE TABLE \"user\"
//(
//    userId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
//    userName VARCHAR(50) NOT NULL,
//    password VARCHAR(255) NOT NULL,
//    userRole VARCHAR(10) CHECK (userRole IN ('user', 'admin', 'ban'))
//);
//
//            String createUsersTable = "CREATE TABLE \"user\"\n" +
//"(\n" +
//"    userId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
//"    userName VARCHAR(50) NOT NULL,\n" +
//"    password VARCHAR(255) NOT NULL,\n" +
//"    userRole VARCHAR(10) CHECK (userRole IN ('user', 'admin', 'ban'))\n" +
//")";

//
//-- 文章表
//CREATE TABLE Articles
//(
//    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
//    title      VARCHAR(255) NOT NULL,
//    content    CLOB         NOT NULL,
//    userId     INT          NOT NULL,
//    updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
//
//           String createArticlesTable = "CREATE TABLE Articles\n" +
//"(\n" +
//"    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
//"    title      VARCHAR(255) NOT NULL,\n" +
//"    content    CLOB         NOT NULL,\n" +
//"    userId     INT          NOT NULL,\n" +
//"    updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
//"    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
//")";

//-- 分类表
//CREATE TABLE category
//(
//    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
//    name        VARCHAR(255) NOT NULL UNIQUE,
//    description ClOB
//);
//
//String createCategoryTable = "CREATE TABLE category\n" +
//"(\n" +
//"    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
//"    name        VARCHAR(255) NOT NULL UNIQUE,\n" +
//"    description CLOB\n" +
//")";

//-- 文章分类表
//CREATE TABLE article_category
//(
//    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
//    article_id  INT NOT NULL,
//    category_id INT NOT NULL,
//    UNIQUE (article_id, category_id)
//);
String createArticleCategoryTable = "CREATE TABLE article_category\n" +
"(\n" +
"    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
"    article_id  INT NOT NULL,\n" +
"    category_id INT NOT NULL,\n" +
"    UNIQUE (article_id, category_id)\n" +
")";

            //执行sql
            statement.execute(createArticleCategoryTable);
            System.out.println("创建用户表成功");
        } catch (SQLException e) {
            System.err.println("SQLException occurred.");
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean saveUser(){
        //插入一条数据
        PreparedStatement pstmtInsert = null;
        PreparedStatement pstmtSelect = null;

        try {
            String insertSQL = "INSERT INTO \"user\" (\"userAccount\", \"userPassword\", \"userName\", \"userAvatar\", \"userProfile\", \"userRole\", \"isDelete\") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmtInsert = conn.prepareStatement(insertSQL);
            pstmtInsert.setString(1, "keke");
            pstmtInsert.setString(2, "123456");
            pstmtInsert.setString(3, "科科");
            pstmtInsert.setString(4, "http://example.com/avatar.jpg");
            pstmtInsert.setString(5, "This is a admin user");
            pstmtInsert.setString(6, "admin");
            pstmtInsert.setInt(7, 0);
            // 执行 SQL 语句
            pstmtInsert.executeUpdate();

            System.out.println("sql插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtInsert != null) pstmtInsert.close();
                if (pstmtSelect != null) pstmtSelect.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public static void selectUser(String userAccount){
        PreparedStatement pstmtSelect = null;

        try {
            String selectSQL = "SELECT * FROM \"user\" WHERE \"userAccount\" = ?";
            pstmtSelect = conn.prepareStatement(selectSQL);
            pstmtSelect.setString(1, userAccount);
            ResultSet resultSet = pstmtSelect.executeQuery();

            // 输出查询结果
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("User Account: " + resultSet.getString("userAccount"));
                System.out.println("User Password: " + resultSet.getString("userPassword"));
                System.out.println("User Name: " + resultSet.getString("userName"));
                System.out.println("User Avatar: " + resultSet.getString("userAvatar"));
                System.out.println("User Profile: " + resultSet.getString("userProfile"));
                System.out.println("User Role: " + resultSet.getString("userRole"));
                System.out.println("Is Delete: " + resultSet.getInt("isDelete"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtSelect != null) pstmtSelect.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

        public static void dropTable() {
        Statement stmt = null;
        try {
            // 创建 Statement 对象
            stmt = conn.createStatement();
            // 删除表的 SQL 语句
            String dropTableSQL = "DROP TABLE Users";

            // 执行删除表的 SQL 语句
            stmt.executeUpdate(dropTableSQL);

            System.out.println("表已成功删除！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
        connectDB();
        
        
        
//        createDBTable(conn);
        
//        saveUser();
        
//        selectUser("keke");
        
//        dropTable();
    }
}
