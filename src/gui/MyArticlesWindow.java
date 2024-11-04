package gui;

import entity.Article;
import service.ArticleService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import context.UserContext;

/**
 * MyArticlesWindow class, responsible for displaying user's articles
 */
public class MyArticlesWindow extends JFrame {

    private JTable articlesTable;
    private JButton backButton;
    private final Color LIGHT_BROWN = new Color(223, 206, 182); 
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18); 
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

    public MyArticlesWindow() {
        List<Article> myArticle = ArticleService.getMyArticle();
        // Initialize components
        initComponents(myArticle);
    }

    private void initComponents(List<Article> articles) {
        // Set window properties
        setTitle("My Articles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close window on exit
        setSize(900, 600); 
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Create articles table
        String[] columnNames = {"Article Title", "Uploader", "Created Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Iterate through articles list and populate the table
        for (Article article : articles) {
            Object[] rowData = {
                    article.getTitle(),
                    article.getUserId(),
                    article.getCreateTime(),
            };
            tableModel.addRow(rowData);
        }

        articlesTable = new JTable(tableModel);
        articlesTable.setFont(DEFAULT_FONT);
        articlesTable.setRowHeight(30);
        articlesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        articlesTable.setBackground(BACKGROUND_COLOR);
        articlesTable.setGridColor(Color.GRAY);

        // Set table header styles
        JTableHeader tableHeader = articlesTable.getTableHeader();
        tableHeader.setFont(DEFAULT_FONT); 
        tableHeader.setBackground(BACKGROUND_COLOR);
        tableHeader.setForeground(Color.BLACK);

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

        // Add double click event to view article details
        articlesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // Double-click event
                    int row = articlesTable.getSelectedRow();  // Get the index of the row that was double-clicked
                    if (row != -1) {
                        String articleTitle = articlesTable.getValueAt(row, 0).toString();  // Get the article title
                        String articleContent = articles.get(row).getContent();  // Get the article content
                        String uploader = UserContext.getInstance().getCurrentUser().getUserName();  // Get uploader information

                        // Create and display article detail window
                        MyArticleDetailWindow articleDetailWindow = new MyArticleDetailWindow(articleTitle, articleContent, uploader);
                        articleDetailWindow.setVisible(true);
                    }
                }
            }
        });

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
}
