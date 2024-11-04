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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.UserService;
import entity.UserRoleEnum;

/**
 * LoginDialog class, responsible for handling the login dialog interface
 */
public class LoginDialog extends JDialog {
    private Frame owner;
    private final Color LIGHT_BROWN = new Color(223, 206, 182); 
    private final Font DEFAULT_FONT = new Font("Georgia", Font.BOLD, 18); 

    public LoginDialog(Frame owner) {
        super(owner, "Login", true);
        this.owner = owner;
        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(owner);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Create labels and text fields for username and password
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 120, 30);
        usernameLabel.setFont(DEFAULT_FONT);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(180, 50, 250, 30); 
        usernameField.setFont(DEFAULT_FONT);
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 120, 30);
        passwordLabel.setFont(DEFAULT_FONT);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(180, 100, 250, 30); 
        passwordField.setFont(DEFAULT_FONT);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        add(passwordField);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(200, 180, 100, 40); 
        styleButton(loginButton);
        add(loginButton);

        // Login button click event
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username != null && !username.trim().isEmpty()) {
                    boolean result = UserService.login(username, password);
                    // If login is successful
                    if (result) {
                        if (UserService.hasRole(UserRoleEnum.ADMIN)) {
                            showCustomMessageDialog("Login successful for admin: " + username);
                            AdminManagementPage adminPage = new AdminManagementPage();
                            adminPage.setVisible(true);

                            // Close HomeWindow and login dialog
                            owner.dispose();
                            dispose();
                        } else if (UserService.hasRole(UserRoleEnum.USER)) {
                            showCustomMessageDialog("Login successful for user: " + username);
                            UserWindow userWindow = new UserWindow();
                            userWindow.setVisible(true);

                            // Close HomeWindow and login dialog
                            owner.dispose();
                            dispose();
                        } else {
                            showCustomMessageDialog("User has been banned: " + username);
                        }
                    } else {
                        showCustomMessageDialog("Incorrect username or password: " + username);
                    }
                } else {
                    showCustomMessageDialog("Invalid username or password.");
                }
            }
        });
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

    // Custom message dialog
    private void showCustomMessageDialog(String message) {
        JDialog dialog = new JDialog(this, "Message", true);
        dialog.setSize(400, 200);
        dialog.setLayout(null);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBounds(50, 30, 300, 50);
        messageLabel.setFont(DEFAULT_FONT);
        messageLabel.setForeground(Color.BLACK);
        dialog.add(messageLabel);

        JButton okButton = new JButton("OK");
        okButton.setBounds(150, 100, 100, 40);
        styleButton(okButton);
        dialog.add(okButton);

        okButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
}
