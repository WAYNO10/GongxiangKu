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

/**
 * UserWindow 类，负责用户登录后的界面
 */
public class UserWindow extends JFrame {
    public UserWindow() {
        // 设置窗口属性
        setTitle("User Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 618);
        setLayout(null);  // 绝对布局

        // 创建返回按钮
        JButton backButton = new JButton("back") {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillOval(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                if (g instanceof Graphics2D) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getForeground());
                    g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
                }
            }
        };
        backButton.setBounds(20, 20, 60, 60);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(true);
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setOpaque(false);
        backButton.addActionListener(e -> {
            // 返回到主窗口
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose(); // 关闭当前窗口
        });
        add(backButton);

        // 创建上传文章按钮
        JButton uploadButton = new JButton("上传文章");
        uploadButton.setBounds(400, 200, 200, 50);
        add(uploadButton);

        // 创建我的文章按钮
        JButton myArticlesButton = new JButton("我的文章");
        myArticlesButton.setBounds(400, 300, 200, 50);
        add(myArticlesButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserWindow userWindow = new UserWindow();
            userWindow.setVisible(true);
        });
    }
}
