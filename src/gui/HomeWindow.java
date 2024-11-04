package gui;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author W.A.Y.N.O
 */
import context.UserContext;
import entity.User;
import service.UserService;
import entity.UserRoleEnum;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import entity.Category;
import service.CategoryService;
import java.util.stream.Collectors;

public class HomeWindow extends JFrame {

    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;
    private JButton myButton;
    private JButton searchButton;
    private JScrollPane scrollPane;

    private List<String> categories = null;
    private final Color LIGHT_BROWN = new Color(223, 206, 182); 
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18); 

    public HomeWindow() {
        List<Category> allCategory = CategoryService.getAllCategory();
        categories = allCategory.stream().map(Category::getName).collect(Collectors.toList());
        initComponents();
        setupEventListeners();
    }

    private void initComponents() {
        setTitle("Home Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Create and configure the search box
        searchField = new JTextField("Search by Label");
        searchField.setBounds(300, 200, 300, 30);
        searchField.setForeground(Color.LIGHT_GRAY);
        searchField.setFont(DEFAULT_FONT);  // Set uniform font type
        searchField.setBorder(BorderFactory.createEmptyBorder());
        add(searchField);

        // Create and configure the suggestion list
        listModel = new DefaultListModel<>();
        suggestionList = new JList<>(listModel);
        suggestionList.setBackground(Color.WHITE);
        suggestionList.setFont(DEFAULT_FONT);  
        suggestionList.setBorder(BorderFactory.createEmptyBorder());
        suggestionList.setVisible(false);

        // Add a scroll pane for the suggestion list
        scrollPane = new JScrollPane(suggestionList);
        scrollPane.setBounds(300, 230, 300, 100);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVisible(false);

        // Apply custom scrollbar UI
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        add(scrollPane);

        // Create and configure "My" button
        myButton = new JButton("My");
        myButton.setBounds(780, 20, 80, 30);
        styleButton(myButton, true);
        add(myButton);

        // Create and configure the "Search" button
        searchButton = new JButton("Search");
        searchButton.setBounds(610, 200, 100, 30); 
        styleButton(searchButton, false);
        add(searchButton);
    }

    private void styleButton(JButton button, boolean isMyButton) {
        button.setFont(DEFAULT_FONT); 
        button.setFocusPainted(false);
        button.setBorderPainted(false); // Remove border
        button.setContentAreaFilled(false); // Remove background fill
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change mouse cursor to hand

        if (isMyButton) {
            button.setBackground(Color.LIGHT_GRAY); 
            button.setForeground(Color.DARK_GRAY);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    button.setForeground(LIGHT_BROWN); 
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    button.setForeground(Color.DARK_GRAY); 
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(LIGHT_BROWN); 
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(Color.DARK_GRAY); 
                }
            });
        } else {
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

    private void setupEventListeners() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search by Label")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
                suggestionList.setVisible(true);
                scrollPane.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search by Label");
                    searchField.setForeground(Color.LIGHT_GRAY);
                }
                if (searchField.getText().isEmpty() || searchField.getText().equals("Search by Label")) {
                    suggestionList.setVisible(false);
                    scrollPane.setVisible(false);
                }
            }
        });

        myButton.addActionListener(e -> {
            User currentUser = UserContext.getInstance().getCurrentUser();
            if (currentUser != null) {
                if (UserService.hasRole(UserRoleEnum.ADMIN)) {
                    AdminManagementPage adminPage = new AdminManagementPage();
                    adminPage.setVisible(true);
                    dispose();
                } else if (UserService.hasRole(UserRoleEnum.USER)) {
                    UserWindow userWindow = new UserWindow();
                    userWindow.setVisible(true);
                    dispose();
                }
            } else {
                LoginDialog loginDialog = new LoginDialog(HomeWindow.this);
                loginDialog.setVisible(true);
            }
        });

        searchButton.addActionListener(e -> {
            String category = searchField.getText();
            SearchResultsWindow searchResultsWindow = new SearchResultsWindow(category);
            searchResultsWindow.setVisible(true);
        });

        suggestionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = suggestionList.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        String selectedCategory = suggestionList.getModel().getElementAt(index);
                        SearchResultsWindow searchResultsWindow = new SearchResultsWindow(selectedCategory);
                        searchResultsWindow.setVisible(true);
                    }
                }
            }
        });
    }

    private void updateSuggestions() {
        String input = searchField.getText().toLowerCase();
        listModel.clear();

        if (!input.isEmpty() && !input.equals("search by label")) {
            for (String category : categories) {
                if (category.toLowerCase().startsWith(input)) {
                    listModel.addElement(category);
                }
            }
        }

        boolean hasSuggestions = listModel.size() > 0;
        suggestionList.setVisible(hasSuggestions);
        scrollPane.setVisible(hasSuggestions);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
        });
    }
}
