import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db_v1 {
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
            
            // 查询所有表
            String query = "SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE = 'T'";
            ResultSet rs = stmt.executeQuery(query);

            // 输出表名
            System.out.println("Tables in the database:");
            while (rs.next()) {
                System.out.println(rs.getString("TABLENAME"));
            }

            rs.close(); // 关闭 ResultSet

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

