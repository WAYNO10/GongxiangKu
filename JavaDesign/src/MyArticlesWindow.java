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
        JScrollPane scrollPane = new JScrollPane(articlesTable);

        // 禁止编辑表格中的数据
        articlesTable.setDefaultEditor(Object.class, null);

        add(scrollPane, BorderLayout.CENTER);

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
