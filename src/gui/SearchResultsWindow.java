package gui;/*
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import entity.Article;
import entity.Category;
import java.text.SimpleDateFormat;
import java.util.Date;
import service.ArticleCategoryService;
import service.CategoryService;
import service.UserService;
import service.ArticleService;

/**
 * SearchResultsWindow class, responsible for displaying search results
 */
public class SearchResultsWindow extends JFrame {

    private JTable resultsTable;
    private JComboBox<String> sortComboBox;
    private JButton homeButton;
    private DefaultTableModel tableModel;
    private Object[][] data = null;
    private final Color LIGHT_BROWN = new Color(223, 206, 182);
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18);
    private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private final Color TEXT_COLOR = new Color(51, 51, 51);

    public SearchResultsWindow(String categoryName) {
        Category category = CategoryService.getCategoryByName(categoryName);
        List<Article> articles = ArticleCategoryService.getArticleByCategoryId(category.getId());
        
        // Initialize data array
        data = new String[articles.size()][3];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Fill the data array with articles' information
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            data[i][0] = article.getTitle();
            data[i][1] = UserService.getUserById(article.getUserId()).getUserName();
            data[i][2] = sdf.format(new Date(article.getCreateTime().getTime()));
        }

        // Initialize components
        initComponents();
    }

    private void initComponents() {
        // Set window properties
        setTitle("Search Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close window on exit
        setSize(900, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Create sorting combo box
        String[] sortOptions = {"Sort by Popularity", "Sort by Latest"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setFont(DEFAULT_FONT);
        sortComboBox.setBackground(BACKGROUND_COLOR);
        sortComboBox.setForeground(TEXT_COLOR);
        sortComboBox.setBorder(BorderFactory.createEmptyBorder());
        sortComboBox.setOpaque(false);
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                sortResults(selectedOption);
            }
        });

        sortComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sortComboBox.setForeground(LIGHT_BROWN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sortComboBox.setForeground(TEXT_COLOR);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setBackground(BACKGROUND_COLOR);
        JLabel sortLabel = new JLabel("Sort By: ");
        sortLabel.setFont(DEFAULT_FONT);
        sortLabel.setForeground(TEXT_COLOR);
        topPanel.add(sortLabel);
        topPanel.add(sortComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Create search results table
        String[] columnNames = {"Article Title", "Uploader", "Created Time"};

        // Use DefaultTableModel to allow data modification
        tableModel = new DefaultTableModel(data, columnNames);
        resultsTable = new JTable(tableModel);
        resultsTable.setFont(DEFAULT_FONT);
        resultsTable.setRowHeight(30);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        resultsTable.setBackground(BACKGROUND_COLOR);
        resultsTable.setGridColor(Color.GRAY);
        resultsTable.setForeground(TEXT_COLOR);

        // Disable table cell editing
        resultsTable.setDefaultEditor(Object.class, null);

        // Create and configure scroll pane
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        // Apply custom scrollbar UI
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);

        // Add double-click event to table to open article details
        resultsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {  // Double-click event
                    int row = resultsTable.getSelectedRow();  // Get the row index of the double-clicked row
                    if (row != -1) {
                        String articleTitle = resultsTable.getValueAt(row, 0).toString();
                        Article article = ArticleService.getArticleByTitle(articleTitle);
                        String articleContent = article.getContent();  // Example content
                        String uploader = UserService.getUserById(article.getUserId()).getUserName();  // Example uploader

                        ReadWindow readWindow = new ReadWindow(articleTitle, articleContent, uploader);
                        readWindow.setVisible(true);
                    }
                }
            }
        });

        // Create home button
        homeButton = new JButton("Home");
        styleButton(homeButton);
        homeButton.addActionListener(e -> {
            // Logic to return to the home page
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose();  // Close the current window
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND_COLOR);
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);
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

    private void sortResults(String sortOption) {
        // Get current table data
        int rowCount = tableModel.getRowCount();
        Object[][] data = new Object[rowCount][tableModel.getColumnCount()];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                data[i][j] = tableModel.getValueAt(i, j);
            }
        }

        // Sort based on selected option
        List<Object[]> dataList = Arrays.asList(data);
        if ("Sort by Popularity".equals(sortOption)) {
            dataList.sort((o1, o2) -> Integer.compare((int) o2[1], (int) o1[1]));
        } else if ("Sort by Latest".equals(sortOption)) {
            dataList.sort((o1, o2) -> ((String) o2[2]).compareTo((String) o1[2]));
        }

        // Update table data
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(dataList.get(i)[j], i, j);
            }
        }
    }
}
