/*
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * SearchResultsWindow 类，负责显示搜索结果的界面
 */
public class SearchResultsWindow extends JFrame {

    private JTable resultsTable;
    private JComboBox<String> sortComboBox;
    private JButton backButton;
    private JButton homeButton;
    private DefaultTableModel tableModel;

    public SearchResultsWindow() {
        // 初始化组件
        initComponents();
    }

    private void initComponents() {
        // 设置窗口属性
        setTitle("Search Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 关闭时销毁窗口
        setSize(800, 600);
        setLayout(new BorderLayout());

        // 创建排序下拉框
        String[] sortOptions = {"阅读量排序", "最新生成时间排序"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                sortResults(selectedOption);
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("排序方式: "));
        topPanel.add(sortComboBox);
        add(topPanel, BorderLayout.NORTH);

        // 创建搜索结果表格
        String[] columnNames = {"文章标题", "阅读量", "生成时间"};
        Object[][] data = {
                {"文章1 - Java基础", 1200, "2023-10-01"},
                {"文章2 - 如何做菜", 800, "2023-10-05"},
                {"文章3 - 投资建议", 1500, "2023-09-28"},
                {"文章4 - 健康生活方式", 600, "2023-10-03"},
                {"文章5 - 设计原则", 2000, "2023-09-30"}
        };

        // 使用 DefaultTableModel 设置数据，以便后续修改数据
        tableModel = new DefaultTableModel(data, columnNames);
        resultsTable = new JTable(tableModel);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 禁用自动调整大小，以便滚动条出现
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 禁止编辑表格中的数据
        resultsTable.setDefaultEditor(Object.class, null);

        add(scrollPane, BorderLayout.CENTER);

        // 创建返回按钮
        homeButton = new JButton("返回首页");
        homeButton.addActionListener(e -> {
            // 返回首页逻辑 (可以是打开HomeWindow，具体实现根据项目结构调整)
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose(); // 关闭当前窗口
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(homeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void sortResults(String sortOption) {
        // 获取当前表格中的数据
        int rowCount = tableModel.getRowCount();
        Object[][] data = new Object[rowCount][tableModel.getColumnCount()];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                data[i][j] = tableModel.getValueAt(i, j);
            }
        }

        // 根据选择的排序方式进行排序
        List<Object[]> dataList = Arrays.asList(data);
        if ("阅读量排序".equals(sortOption)) {
            dataList.sort((o1, o2) -> Integer.compare((int) o2[1], (int) o1[1])); // 按阅读量降序排序
        } else if ("最新生成时间排序".equals(sortOption)) {
            dataList.sort((o1, o2) -> ((String) o2[2]).compareTo((String) o1[2])); // 按时间降序排序
        }

        // 更新表格数据
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(dataList.get(i)[j], i, j);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchResultsWindow searchResultsWindow = new SearchResultsWindow();
            searchResultsWindow.setVisible(true);
        });
    }
}
