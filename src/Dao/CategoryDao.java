package Dao;

import entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public static final String URL = "jdbc:derby:.\\mydatabase;create=true";

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
     * 添加分类
     * @param category
     * @return
     */
    public Integer addCategory(Category category) {
        String sql = "INSERT INTO category (name, description) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

        public Category getCategoryById(Integer categoryId) {
        String sql = "SELECT * FROM category WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));

                    return category;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return null; 
    }

        public Category getCategoryByName(String categoryName) {
        String sql = "SELECT * FROM category WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoryName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));

                    return category;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }
}
