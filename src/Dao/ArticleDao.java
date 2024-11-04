/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Article;

/**
 *
 * @author 王陈宇科
 */
public class ArticleDao {
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

    // 插入文章
    public int insertArticle(Article article){

        String sql = "INSERT INTO Articles (title, content, userId) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);){

            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setInt(3, article.getUserId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting article failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    article.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Inserting article failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return article.getId();
    }
    
    // 根据ID查询文章
    public Article getArticleByTitle(String title){
        String sql = "SELECT * FROM Articles WHERE title = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getInt("userId"),
                        resultSet.getTimestamp("updateTime"),
                        resultSet.getTimestamp("createTime")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 删除文章
    public boolean deleteArticle(int id){
        int affectedRows = 0;
        String sql = "DELETE FROM Articles WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新文章
    public boolean updateArticle(Article article){
        // 构建SQL语句的SET部分
        List<String> setClauses = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (article.getTitle() != null) {
            setClauses.add("title = ?");
            params.add(article.getTitle());
        }
        if (article.getContent() != null) {
            setClauses.add("content = ?");
            params.add(article.getContent());
        }
        if (article.getUserId() != 0) {
            setClauses.add("userId = ?");
            params.add(article.getUserId());
        }
        // 其他字段...

        if (setClauses.isEmpty()) {
            return false; // 没有字段需要更新
        }

        StringBuilder sql = new StringBuilder("UPDATE articles SET ");
        sql.append(String.join(", ", setClauses));
        sql.append(" WHERE id = ?");
        params.add(article.getId());

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;
            for (Object param : params) {
                if (param instanceof String) {
                    pstmt.setString(paramIndex++, (String) param);
                } else if (param instanceof Integer) {
                    pstmt.setInt(paramIndex++, (Integer) param);
                } else if (param instanceof Timestamp) {
                    pstmt.setTimestamp(paramIndex++, (Timestamp) param);
                }
                // 添加其他类型处理...
            }

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // 如果有行被更新，则返回true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 更新失败
        }
    }

    // 查询所有文章
    public List<Article> getAllArticles(){
        String sql = "SELECT * FROM Articles";
        List<Article> articles = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){

            ResultSet resultSet = preparedStatement.executeQuery();

            articles = new ArrayList<>();
            while (resultSet.next()) {
                Article article = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getInt("userId"),
                        resultSet.getTimestamp("updateTime"),
                        resultSet.getTimestamp("createTime")
                );
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return articles;
    }
    
        //根据创建用户id获取文章
    public List<Article> getArticlesByUserId(Integer userId) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM articles WHERE userId = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId); // 设置参数

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    article.setUserId(rs.getInt("userId"));
                    article.setCreateTime(rs.getTimestamp("createTime"));
                    article.setUpdateTime(rs.getTimestamp("updateTime"));
                    articles.add(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常，例如抛出自定义异常或记录日志
        }

        return articles;
    }

    // 根据ID查询文章
    public Article getArticleById(int id){
        String sql = "SELECT * FROM Articles WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getInt("userId"),
                        resultSet.getTimestamp("updateTime"),
                        resultSet.getTimestamp("createTime")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
       //根据id列表批量查询文章数据
    public List<Article> getArticlesByIds(List<Integer> articleIds) {
        List<Article> articles = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM articles WHERE id IN (");
        for (int i = 0; i < articleIds.size(); i++) {
            if (i > 0) sqlBuilder.append(", ");
            sqlBuilder.append('?');
        }
        sqlBuilder.append(')');

        String sql = sqlBuilder.toString();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int index = 1;
            for (Integer id : articleIds) {
                pstmt.setInt(index++, id);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Article article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setTitle(rs.getString("title"));
                    article.setContent(rs.getString("content"));
                    article.setUserId(rs.getInt("userId"));
                    article.setCreateTime(rs.getTimestamp("createTime"));
                    article.setUpdateTime(rs.getTimestamp("updateTime"));;

                    articles.add(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articles;
    }
}
