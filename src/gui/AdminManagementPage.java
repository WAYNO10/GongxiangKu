package gui;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author W.A.Y.N.O
 */
import entity.Article;
import service.ArticleService;
import service.UserService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * AdminManagementPage class, responsible for managing articles by the administrator.
 */
public class AdminManagementPage extends JFrame {

    private JTable articlesTable;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private Object[][] data = null;
    private final Color LIGHT_BROWN = new Color(223, 206, 182); 
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18); 
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY; 

    public AdminManagementPage() {
        List<Article> articles = ArticleService.getAllArticles();
        
        data = new String[articles.size()][3];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            data[i][0] = article.getTitle();
            data[i][1] = UserService.getUserById(article.getUserId()).getUserName();
            data[i][2] = sdf.format(new Date(article.getCreateTime().getTime()));
        }

        
        initComponents();
    }

    private void initComponents() {
        
        setTitle("Admin Management Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setSize(900, 600); 
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR); // Set background color to light gray

        // Create the articles table
        String[] columnNames = {"Article Title", "Uploader", "Upload Time"};

        // Use DefaultTableModel to set data
        tableModel = new DefaultTableModel(data, columnNames);
        articlesTable = new JTable(tableModel);
        articlesTable.setFont(DEFAULT_FONT); // Set table font
        articlesTable.setRowHeight(30); // Set row height
        articlesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Automatically resize all columns
        articlesTable.setBackground(BACKGROUND_COLOR); // Set table background color
        articlesTable.setGridColor(Color.GRAY); // Set table grid line color

        // Set table header style
        JTableHeader tableHeader = articlesTable.getTableHeader();
        tableHeader.setFont(DEFAULT_FONT);
        tableHeader.setBackground(BACKGROUND_COLOR);
        tableHeader.setForeground(Color.BLACK); // Set header text color

        articlesTable.setDefaultEditor(Object.class, null); // Disable editing of table data

        // Create and configure scroll pane
        JScrollPane scrollPane = new JScrollPane(articlesTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        // Apply custom scrollbar UI
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);

        // Add double-click event to review article details
        articlesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // Double-click event
                    int row = articlesTable.getSelectedRow();  // Get the index of the double-clicked row
                    if (row != -1) {
                        String articleTitle = articlesTable.getValueAt(row, 0).toString();
                        Article article = ArticleService.getArticleByTitle(articleTitle);
                        String articleContent = article.getContent(); // Example article content
                        String uploader = UserService.getUserById(article.getUserId()).getUserName();

                        // Create and display the review article window
                        ReviewArticleWindow reviewArticleWindow = new ReviewArticleWindow(articleTitle, articleContent, uploader);
                        reviewArticleWindow.setVisible(true);
                    }
                }
            }
        });

        // Create back button
        backButton = new JButton("Back to Home");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            // Logic to return to the home window (can open HomeWindow, depending on project structure)
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose(); // Close current window
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND_COLOR); // Set bottom panel background color to match the entire interface
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

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminManagementPage adminPage = new AdminManagementPage();
            adminPage.setVisible(true);
        });
    }
}
