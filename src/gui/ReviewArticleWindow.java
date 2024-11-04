package gui;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author W.A.Y.N.O
 */
import entity.Article;
import entity.ArticleCategory;
import entity.Category;
import service.ArticleCategoryService;
import service.ArticleService;
import service.CategoryService;

import javax.swing.*;
import java.awt.*;

/**
 * ReviewArticleWindow class, responsible for displaying and reviewing the details of an article
 */
public class ReviewArticleWindow extends JFrame {

    private JTextArea articleDetailTextArea;
    private JLabel uploaderLabel;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton backButton;
    private JTextField tagField;
    private final Color LIGHT_BROWN = new Color(223, 206, 182);
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18);
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

    public ReviewArticleWindow(String articleTitle, String articleContent, String uploader) {
        // Initialize components
        initComponents(articleTitle, articleContent, uploader);
    }

    private void initComponents(String articleTitle, String articleContent, String uploader) {
        // Set window properties
        setTitle("Review Article - " + articleTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close window on exit
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Create and configure article detail text area
        articleDetailTextArea = new JTextArea(articleContent);
        articleDetailTextArea.setFont(DEFAULT_FONT);
        articleDetailTextArea.setLineWrap(true);
        articleDetailTextArea.setWrapStyleWord(true);
        articleDetailTextArea.setEditable(false);  // Disable editing
        articleDetailTextArea.setBackground(BACKGROUND_COLOR);
        JScrollPane scrollPane = new JScrollPane(articleDetailTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

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

        // Create approval buttons and tag input field
        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");
        tagField = new JTextField(20);
        tagField.setFont(DEFAULT_FONT);
        tagField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Create tag input panel
        JPanel tagPanel = new JPanel();
        tagPanel.setBackground(BACKGROUND_COLOR);
        JLabel tagLabel = new JLabel("Enter Tag: ");
        tagLabel.setFont(DEFAULT_FONT);
        tagLabel.setForeground(Color.BLACK);
        tagPanel.add(tagLabel);
        tagPanel.add(tagField);

        // Set button actions
        approveButton.addActionListener(e -> {
            String tag = tagField.getText().trim();
            if (tag.isEmpty()) {
                showCustomDialog("Please enter a tag for the article.", JOptionPane.WARNING_MESSAGE);
            } else {
                ArticleCategory articleCategory = new ArticleCategory();
                Article article = ArticleService.getArticleByTitle(articleTitle);
                Category category = CategoryService.getCategoryByName(tag);
                if (category == null) {
                    Category category1 = new Category();
                    category1.setName(tag);
                    category1.setDescription(tag + " description");
                    CategoryService.createCategory(category1);
                    category = CategoryService.getCategoryByName(tag);
                }

                articleCategory.setArticleId(article.getId());
                articleCategory.setCategoryId(category.getId());
                ArticleCategoryService.addArticleCategory(articleCategory);
                showCustomDialog("The article has been approved with tag: " + tag, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        rejectButton.addActionListener(e -> showCustomDialog("The article has been rejected.", JOptionPane.INFORMATION_MESSAGE));

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(tagPanel);

        // Style buttons and add to panel
        styleButton(approveButton);
        styleButton(rejectButton);
        styleButton(backButton = new JButton("Back"));

        backButton.addActionListener(e -> dispose()); // Close window on back button click
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
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

    private void showCustomDialog(String message, int messageType) {
        UIManager.put("OptionPane.messageFont", DEFAULT_FONT);
        UIManager.put("OptionPane.buttonFont", DEFAULT_FONT);
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("Button.background", LIGHT_BROWN);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("OptionPane.okButtonText", "OK"); 

        // Show the dialog
        JOptionPane.showMessageDialog(this, message, "Message", messageType);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReviewArticleWindow reviewWindow = new ReviewArticleWindow("Art", "This is an Art Article.", "Tom");
            reviewWindow.setVisible(true);
        });
    }
}
