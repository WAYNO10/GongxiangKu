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
 * MyArticlesWindow 类，负责用户查看自己文章的界面
 */
public class MyArticlesWindow extends JFrame {

    private JTable articlesTable;
    private JButton backButton;

    public MyArticlesWindow() {
        // 初始化组件
        initComponents();
    }

    private void initComponents() {
        // 设置窗口属性
        setTitle("My Articles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭时销毁窗口
        setSize(800, 600);
        setLayout(new BorderLayout());

        // 创建文章表格
        String[] columnNames = {"文章信息", "状态", "管理员认定类别"};
        Object[][] data = {
                {"文章1 - 关于Java基础的介绍", "审核中", "N/A"},
                {"文章2 - 如何做菜", "通过", "Cooking"},
                {"文章3 - 投资建议", "未通过", "N/A"},
                {"文章4 - 健康生活方式", "审核中", "N/A"},
                {"文章5 - 设计原则", "通过", "Design"}
        };

        // 使用 DefaultTableModel 设置数据，以便后续修改数据
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        articlesTable = new JTable(tableModel);
        articlesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 禁用自动调整大小，以便滚动条出现
        JScrollPane scrollPane = new JScrollPane(articlesTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 禁止编辑表格中的数据
        articlesTable.setDefaultEditor(Object.class, null);

        add(scrollPane, BorderLayout.CENTER);

        // 添加表格双击事件，查看文章详情
        articlesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // 双击事件
                    int row = articlesTable.getSelectedRow();  // 获取被双击的行索引
                    if (row != -1) {
                        String articleTitle = articlesTable.getValueAt(row, 0).toString();  // 获取文章标题
                        String articleContent = "这是关于 " + articleTitle + " 的详细内容。";  // 示例文章内容，可以替换为真实内容

                        // 创建并显示文章详情窗口
                        ArticleDetailWindow articleDetailWindow = new ArticleDetailWindow(articleTitle, articleContent);
                        articleDetailWindow.setVisible(true);
                    }
                }
            }
        });

        // 创建返回按钮
        backButton = new JButton("返回");
        backButton.addActionListener(e -> dispose());  // 点击返回按钮时关闭窗口

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyArticlesWindow myArticlesWindow = new MyArticlesWindow();
            myArticlesWindow.setVisible(true);
        });
    }
}
