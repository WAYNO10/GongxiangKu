/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author W.A.Y.N.O
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * AdminManagementPage 类，负责管理员对文章的审核管理
 */
public class AdminManagementPage extends JFrame {

    private JTable articlesTable;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public AdminManagementPage() {
        // 初始化组件
        initComponents();
    }

    private void initComponents() {
        // 设置窗口属性
        setTitle("Admin Management Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭时销毁窗口
        setSize(800, 600);
        setLayout(new BorderLayout());

        // 创建未审核文章表格
        String[] columnNames = {"文章标题", "上传者", "上传时间"};
        Object[][] data = {
                {"文章1 - Java基础", "用户A", "2023-10-01"},
                {"文章2 - 如何做菜", "用户B", "2023-10-05"},
                {"文章3 - 投资建议", "用户C", "2023-09-28"},
                {"文章4 - 健康生活方式", "用户D", "2023-10-03"},
                {"文章5 - 设计原则", "用户E", "2023-09-30"}
        };

        // 使用 DefaultTableModel 设置数据，以便后续修改数据
        tableModel = new DefaultTableModel(data, columnNames);
        articlesTable = new JTable(tableModel);
        articlesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 禁用自动调整大小，以便滚动条出现
        JScrollPane scrollPane = new JScrollPane(articlesTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 禁止编辑表格中的数据
        articlesTable.setDefaultEditor(Object.class, null);

        add(scrollPane, BorderLayout.CENTER);

        // 添加表格行双击事件以审核文章详情
        articlesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // 双击事件
                    int row = articlesTable.getSelectedRow();  // 获取被双击的行索引
                    if (row != -1) {
                        String articleTitle = articlesTable.getValueAt(row, 0).toString();
                        String articleContent = "这是关于 " + articleTitle + " 的详细内容。";  // 示例文章内容
                        String uploader = "上传者: " + articlesTable.getValueAt(row, 1).toString();

                        // 创建并显示审核文章详情窗口
                        ReviewArticleWindow reviewArticleWindow = new ReviewArticleWindow(articleTitle, articleContent, uploader);
                        reviewArticleWindow.setVisible(true);
                    }
                }
            }
        });

        // 创建返回按钮
        backButton = new JButton("返回首页");
        backButton.addActionListener(e -> {
            // 返回首页逻辑 (可以是打开HomeWindow，具体实现根据项目结构调整)
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose(); // 关闭当前窗口
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminManagementPage adminPage = new AdminManagementPage();
            adminPage.setVisible(true);
        });
    }
}
