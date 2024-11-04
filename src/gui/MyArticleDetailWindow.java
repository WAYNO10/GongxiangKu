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
 * MyArticleDetailWindow class, responsible for displaying detailed information about an article
 */
public class MyArticleDetailWindow extends JFrame {

    private JTextArea articleDetailTextArea;
    private JLabel uploaderLabel;
    private JButton backButton;
    private final Color LIGHT_BROWN = new Color(223, 206, 182); 
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18); 
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY; 

    public MyArticleDetailWindow(String articleTitle, String articleContent, String uploader) {
        // Initialize components
        initComponents(articleTitle, articleContent, uploader);
    }

    private void initComponents(String articleTitle, String articleContent, String uploader) {
        // Set window properties
        setTitle("My Article Detail Window - " + articleTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close window on exit
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR); 

        // Create and configure the article detail text area
        articleDetailTextArea = new JTextArea(articleContent);
        articleDetailTextArea.setFont(DEFAULT_FONT);
        articleDetailTextArea.setLineWrap(true);
        articleDetailTextArea.setWrapStyleWord(true);
        articleDetailTextArea.setEditable(false);  // Disable editing
        articleDetailTextArea.setBackground(BACKGROUND_COLOR); 
        JScrollPane scrollPane = new JScrollPane(articleDetailTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR); // Ensure consistency in scroll area background

        // Apply custom scrollbar UI
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);

        // Display uploader information
        uploaderLabel = new JLabel("Uploaded by: " + uploader);
        uploaderLabel.setFont(DEFAULT_FONT); 
        uploaderLabel.setForeground(Color.BLACK);
        uploaderLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        JPanel uploaderPanel = new JPanel();
        uploaderPanel.setBackground(BACKGROUND_COLOR);
        uploaderPanel.add(uploaderLabel);
        add(uploaderPanel, BorderLayout.NORTH);

        // Create back button
        backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> dispose());  // Close window when back button is clicked

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND_COLOR); 
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setFont(DEFAULT_FONT); 
        button.setFocusPainted(false);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setForeground(Color.BLACK);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                button.setForeground(LIGHT_BROWN); 
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                button.setForeground(Color.BLACK); 
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setForeground(Color.BLACK); 
            }
        });
    }
}
