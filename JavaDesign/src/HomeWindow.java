/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author W.A.Y.N.O
 */
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * HomeWindow 窗口类
 */
public class HomeWindow extends JFrame {

    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;
    private JButton myButton;

    private final String[] categories = {"Art", "Business", "Cooking", "Design", "Education", "Fitness", "Gaming", "Health", "Investment", "Java", "Kotlin", "Music"};

    public HomeWindow() {
        // 初始化组件
        initComponents();
        // 设置事件监听器
        setupEventListeners();
    }

    private void initComponents() {
        // 设置窗口属性
        setTitle("Home Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);  // 绝对布局

        // 创建并配置搜索框
        searchField = new JTextField("Search by Label");
        searchField.setBounds(250, 100, 300, 30);
        searchField.setForeground(Color.LIGHT_GRAY);
        add(searchField);

        // 创建并配置建议列表
        listModel = new DefaultListModel<>();
        suggestionList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(suggestionList);
        scrollPane.setBounds(250, 140, 300, 100);
        suggestionList.setVisible(false);
        add(scrollPane);

        // 创建并配置按钮
        myButton = new JButton("My");
        myButton.setBounds(680, 20, 80, 30);
        add(myButton);
    }

    private void setupEventListeners() {
        // 搜索框文本变化监听
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

        // 搜索框焦点事件监听
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search by Label")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
                suggestionList.setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search by Label");
                    searchField.setForeground(Color.LIGHT_GRAY);
                }
                if (searchField.getText().isEmpty() || searchField.getText().equals("Search by Label")) {
                    suggestionList.setVisible(false);
                }
            }
        });

        // 按钮点击事件
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            // 创建并显示登录对话框
            LoginDialog loginDialog = new LoginDialog(HomeWindow.this);
            loginDialog.setVisible(true);
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
        suggestionList.setVisible(listModel.size() > 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
        });
    }
}
