package gui;/*
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
 * UserWindow class, responsible for the user interface after login
 */
public class UserWindow extends JFrame {
    private final Color LIGHT_BROWN = new Color(223, 206, 182);
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18); 
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private final Color TEXT_COLOR = new Color(51, 51, 51);

    public UserWindow() {
        // Set window properties
        setTitle("User Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 618);
        setLayout(null);  // Absolute layout
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Create back button
        JButton backButton = new JButton("Back");
        styleCircleButton(backButton);
        backButton.setBounds(20, 20, 80, 80);
        backButton.addActionListener(e -> {
            // Return to the home window
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose(); // Close the current window
        });
        add(backButton);

        // Create upload article button
        JButton uploadButton = new JButton("Upload Article");
        uploadButton.setBounds(400, 200, 200, 50);
        styleButton(uploadButton);
        uploadButton.addActionListener(e -> {
            UploadArticleWindow uploadArticleWindow = new UploadArticleWindow();
            uploadArticleWindow.setVisible(true);
        });
        add(uploadButton);

        // Create my articles button
        JButton myArticlesButton = new JButton("My Articles");
        myArticlesButton.setBounds(400, 300, 200, 50);
        styleButton(myArticlesButton);
        myArticlesButton.addActionListener(e -> {
            MyArticlesWindow myArticlesWindow = new MyArticlesWindow();
            myArticlesWindow.setVisible(true);
        });
        add(myArticlesButton);
    }

    private void styleButton(JButton button) {
        button.setFont(DEFAULT_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setForeground(TEXT_COLOR);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setForeground(TEXT_COLOR);
            }
        });
    }

    private void styleCircleButton(JButton button) {
        button.setFont(DEFAULT_FONT.deriveFont(14f));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setForeground(TEXT_COLOR);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setForeground(TEXT_COLOR);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setForeground(TEXT_COLOR);
            }
        });

        button.setPreferredSize(new Dimension(80, 80));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserWindow userWindow = new UserWindow();
            userWindow.setVisible(true);
        });
    }
}
