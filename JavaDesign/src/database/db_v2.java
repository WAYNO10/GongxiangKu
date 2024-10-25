import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class db_v2 {
    public static void main(String[] args) {
        
        // 使用你的无线局域网适配器 IP 地址
        String url = "jdbc:derby://localhost:1527/db1;create=true";
        // 数据库用户名和密码
        String username = "root";
        String password = "1234";

        // 声明连接对象
        Connection conn = null;
        Statement stmt = null;

        try {
            // 创建数据库连接
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to " + url);
            
            // 创建 Statement 对象
            stmt = conn.createStatement();
            
            // 创建用户表
            String createUsersTable = "CREATE TABLE Users ("
                + "UserID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "Username VARCHAR(50) NOT NULL, "
                + "Password VARCHAR(255) NOT NULL, "
                + "Role VARCHAR(10) CHECK (Role IN ('普通用户', '审核员'))"
                + ")";
            stmt.executeUpdate(createUsersTable);
            System.out.println("Users table created.");

            // 创建类别表
            String createCategoriesTable = "CREATE TABLE Categories ("
                + "CategoryID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "CategoryName VARCHAR(100) NOT NULL"
                + ")";
            stmt.executeUpdate(createCategoriesTable);
            System.out.println("Categories table created.");

            // 创建文献表
            String createDocumentsTable = "CREATE TABLE Documents ("
                + "DocumentID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "Title VARCHAR(255) NOT NULL, "
                + "UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + "Status VARCHAR(10) CHECK (Status IN ('待审核', '已通过', '未通过')), "
                + "UserID INT, "
                + "CategoryID INT, "
                + "FOREIGN KEY (UserID) REFERENCES Users(UserID), "
                + "FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)"
                + ")";
            stmt.executeUpdate(createDocumentsTable);
            System.out.println("Documents table created.");

            // 创建审核员表
            String createReviewersTable = "CREATE TABLE Reviewers ("
                + "ReviewerID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "Username VARCHAR(50) NOT NULL, "
                + "Password VARCHAR(255) NOT NULL"
                + ")";
            stmt.executeUpdate(createReviewersTable);
            System.out.println("Reviewers table created.");

            // 创建审核记录表
            String createReviewLogsTable = "CREATE TABLE ReviewLogs ("
                + "ReviewID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "DocumentID INT, "
                + "ReviewerID INT, "
                + "ReviewDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + "Decision VARCHAR(10) CHECK (Decision IN ('通过', '未通过')), "
                + "FOREIGN KEY (DocumentID) REFERENCES Documents(DocumentID), "
                + "FOREIGN KEY (ReviewerID) REFERENCES Reviewers(ReviewerID)"
                + ")";
            stmt.executeUpdate(createReviewLogsTable);
            System.out.println("ReviewLogs table created.");

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } finally {
            // 确保关闭连接
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.err.println("Error closing statement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println("Error closing connection: " + ex.getMessage());
                }
            }
        }
    }
}
