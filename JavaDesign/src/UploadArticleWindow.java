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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UploadArticleWindow 类，负责用户上传文章的界面
 */
public class UploadArticleWindow extends JFrame {

    private JTextArea articleTextArea;
    private JButton uploadButton;
    private JButton cancelButton;

    public UploadArticleWindow() {
        // 初始化组件
        initComponents();
    }

    private void initComponents() {
        // 设置窗口属性
        setTitle("Upload Article");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭时销毁窗口
        setSize(800, 600);
        setLayout(null);  // 绝对布局

        // 创建文章输入框
        articleTextArea = new JTextArea("文章输入框");
        articleTextArea.setLineWrap(true);
        articleTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(articleTextArea);
        scrollPane.setBounds(50, 50, 700, 350);  // 定位输入框的位置和大小
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        // 创建上传按钮
        uploadButton = new JButton("上传");
        uploadButton.setBounds(200, 450, 100, 40);  // 定位上传按钮的位置
        add(uploadButton);

        // 创建取消按钮
        cancelButton = new JButton("取消");
        cancelButton.setBounds(500, 450, 100, 40);  // 定位取消按钮的位置
        add(cancelButton);

        // 设置上传按钮的事件监听
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String articleContent = articleTextArea.getText();
                // 这里可以添加上传文章的逻辑，比如将文章保存到数据库或文件
                JOptionPane.showMessageDialog(UploadArticleWindow.this, "文章已上传:\n" + articleContent);
            }
        });

        // 设置取消按钮的事件监听
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击取消按钮时关闭窗口
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UploadArticleWindow uploadArticleWindow = new UploadArticleWindow();
            uploadArticleWindow.setVisible(true);
        });
    }
}
