package gui;

import javax.swing.*;
import java.awt.*;
import service.ArticleService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Article;

/**
 * UploadArticleWindow class, responsible for allowing users to upload articles
 */
public class UploadArticleWindow extends JFrame {

    private JTextField titleTextField;
    private JTextArea articleTextArea;
    private JButton uploadButton;
    private JButton cancelButton;
    private final Color LIGHT_BROWN = new Color(223, 206, 182);
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18);
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private final Color TEXT_COLOR = new Color(51, 51, 51);

    public UploadArticleWindow() {
        // Initialize components
        initComponents();
    }

    private void initComponents() {
        // Set window properties
        setTitle("Upload Article");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Dispose of window on close
        setSize(900, 600);
        setLayout(null);  // Absolute layout
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Create title input field
        titleTextField = new JTextField("Enter Article Title");
        titleTextField.setBounds(50, 20, 800, 40);
        titleTextField.setFont(DEFAULT_FONT);
        titleTextField.setForeground(TEXT_COLOR);
        titleTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(titleTextField);

        // Create article content input area
        articleTextArea = new JTextArea("Enter Article Content");
        articleTextArea.setFont(DEFAULT_FONT);
        articleTextArea.setLineWrap(true);
        articleTextArea.setWrapStyleWord(true);
        articleTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        articleTextArea.setForeground(TEXT_COLOR);
        JScrollPane scrollPane = new JScrollPane(articleTextArea);
        scrollPane.setBounds(50, 80, 800, 350);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        // Apply custom scrollbar UI
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        add(scrollPane);

        // Create upload button
        uploadButton = new JButton("Upload");
        styleButton(uploadButton);
        uploadButton.setBounds(200, 450, 150, 50);
        add(uploadButton);

        // Create cancel button
        cancelButton = new JButton("Cancel");
        styleButton(cancelButton);
        cancelButton.setBounds(500, 450, 150, 50);
        add(cancelButton);

        // Set action listener for the upload button
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String articleTitle = titleTextField.getText();
                String articleContent = articleTextArea.getText();
                Article article = new Article();
                article.setTitle(articleTitle);
                article.setContent(articleContent);
                ArticleService.addArticle(article);

                // Custom dialog to display upload success
                showCustomMessageDialog("Article Uploaded:", "Title: " + articleTitle + "\nContent: " + articleContent);
            }
        });

        // Set action listener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close window when cancel button is clicked
                dispose();
            }
        });
    }

    private void showCustomMessageDialog(String title, String message) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(BACKGROUND_COLOR);

        // Create message label
        JLabel messageLabel = new JLabel("<html><body style='text-align: center;'>" + message.replaceAll("\n", "<br>") + "</body></html>", SwingConstants.CENTER);
        messageLabel.setFont(DEFAULT_FONT);
        messageLabel.setForeground(TEXT_COLOR);
        dialog.add(messageLabel, BorderLayout.CENTER);

        // Create OK button
        JButton okButton = new JButton("OK");
        styleButton(okButton);
        okButton.addActionListener(e -> dialog.dispose());

        // Create button panel and add the OK button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog
        dialog.setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UploadArticleWindow uploadArticleWindow = new UploadArticleWindow();
            uploadArticleWindow.setVisible(true);
        });
    }
}
