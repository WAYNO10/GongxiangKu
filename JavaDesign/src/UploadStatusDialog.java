/*
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
 * UploadStatusDialog 类，负责显示上传状态的弹窗
 */
public class UploadStatusDialog extends JDialog {

    public UploadStatusDialog(Frame owner, boolean isSuccess) {
        super(owner, "Upload Status", true);
        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);

        // 创建消息标签
        JLabel messageLabel = new JLabel(isSuccess ? "上传成功！" : "上传失败，请重试。", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(messageLabel, BorderLayout.CENTER);

        // 创建确认按钮
        JButton okButton = new JButton("确定");
        okButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UploadStatusDialog uploadStatusDialog = new UploadStatusDialog(null, true);
            uploadStatusDialog.setVisible(true);
        });
    }
}
