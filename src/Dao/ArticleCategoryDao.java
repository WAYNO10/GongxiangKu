package Dao;

import java.sql.*;
import entity.ArticleCategory;
import java.util.ArrayList;
import java.util.List;
import entity.Article;

public class ArticleCategoryDao {

    public static final String URL = "jdbc:derby://localhost:1527/mydatabase;create=true";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "123";


    private Connection getConnection() throws SQLException {

        Connection conn = null;
        try {
            //创建数据库连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(URL + " connected...");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return conn;
    }

    //新增文章分类 并返回id
    public void addArticleCategory(ArticleCategory articleCategory) {
        String sql = "INSERT INTO article_category (article_id, category_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, articleCategory.getArticleId());
            pstmt.setInt(2, articleCategory.getCategoryId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting article_category failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        //获取某个分类下所有文章数据
    //获取某个分类下所有文章id
    public List<Integer> getArticleIdsByCategoryId(int categoryId) {
        List<Integer> articleIds = new ArrayList<>();
        String sql = "SELECT article_id FROM article_category WHERE category_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int articleId = rs.getInt("article_id");
                    articleIds.add(articleId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常，例如抛出自定义异常或记录日志
        }

        return articleIds;
    }

    //删除文章分类
    public boolean removeArticleCategory(ArticleCategory articleCategory) {
        String sql = "DELETE FROM article_category WHERE article_id = ? AND category_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, articleCategory.getArticleId());
            pstmt.setInt(2, articleCategory.getCategoryId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除某个文章所有关联数据
    public boolean removeArticleCategoryByArticleId(int articleId) {
        String sql = "DELETE FROM article_category WHERE article_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, articleId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
        //查询所有关联数据
    public List<ArticleCategory> getAllArticleCategories() {
        List<ArticleCategory> articleCategories = new ArrayList<>();
        String sql = "SELECT * FROM article_category";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ArticleCategory articleCategory = new ArticleCategory();
                articleCategory.setId(rs.getInt("id"));
                articleCategory.setArticleId(rs.getInt("article_id"));
                articleCategory.setCategoryId(rs.getInt("category_id"));

                articleCategories.add(articleCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleCategories;
    }
}
