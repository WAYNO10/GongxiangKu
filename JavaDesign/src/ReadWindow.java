/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author W.A.Y.N.O
 */
import javax.swing.*;
import java.awt.*;

/**
 * ReadWindow 类，负责显示文章的详细信息
 */
public class ReadWindow extends JFrame {

    private JTextArea articleDetailTextArea;
    private JLabel uploaderLabel;
    private JButton backButton;

    public ReadWindow(String articleTitle, String articleContent, String uploader) {
        // 初始化组件
        initComponents(articleTitle, articleContent, uploader);
    }

    private void initComponents(String articleTitle, String articleContent, String uploader) {
        // 设置窗口属性
        setTitle("Read Window - " + articleTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭时销毁窗口
        setSize(800, 600);
        setLayout(new BorderLayout());

        // 创建并配置文章详情文本区域
        articleDetailTextArea = new JTextArea(articleContent);
        articleDetailTextArea.setLineWrap(true);
        articleDetailTextArea.setWrapStyleWord(true);
        articleDetailTextArea.setEditable(false);  // 禁止用户修改
        JScrollPane scrollPane = new JScrollPane(articleDetailTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // 显示上传者信息
        uploaderLabel = new JLabel(uploader);
        add(uploaderLabel, BorderLayout.NORTH);

        // 创建返回按钮
        backButton = new JButton("返回上一页");
        backButton.addActionListener(e -> dispose());  // 点击返回按钮时关闭窗口

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 示例：创建并显示文章详情窗口
            ReadWindow articleDetailWindow = new ReadWindow("示例文章", "这是一篇示例文章的详细内容。", "上传者: 用户A");
            articleDetailWindow.setVisible(true);
        });
    }
}
