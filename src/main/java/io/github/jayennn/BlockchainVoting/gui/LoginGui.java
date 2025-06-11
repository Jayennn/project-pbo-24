package io.github.jayennn.BlockchainVoting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import io.github.jayennn.BlockchainVoting.controller.Login;

public class LoginGui extends JPanel {
    private final GuiManager guiManager;
    private final Login loginController;

    public LoginGui(GuiManager guiManager, Login loginController) {
        this.guiManager = guiManager;
        this.loginController = loginController;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/assets/login-background.png"));
        java.net.URL bgUrl = getClass().getResource("/assets/login-background.png");
        if (bgUrl == null) {
            System.out.println("gambar gak ada");
        } else {
            System.out.println("gambar ada" + bgUrl);
        }
        
        JLabel background = new JLabel(bgIcon);
        background.setLayout(new BorderLayout());
        add(background, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255, 220));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 215)),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        addFormComponents(formPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        background.add(formPanel, BorderLayout.CENTER);
    }

    private void addFormComponents(JPanel formPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Username"), gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        formPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> handleLogin(
            usernameField.getText(),
            new String(passwordField.getPassword())
        ));
    }

    private void handleLogin(String username, String password) {
        //contoh doang
        String validUsername = "admin";
        String validPassword = "1234";

        if (!username.equals(validUsername)) {
            JOptionPane.showMessageDialog(this, "nama gak ada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(validPassword)) {
            JOptionPane.showMessageDialog(this, "Password salah ", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        guiManager.showDashboardAdmin();
    }

}