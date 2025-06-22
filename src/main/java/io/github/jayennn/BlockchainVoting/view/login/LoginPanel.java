package io.github.jayennn.BlockchainVoting.view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import io.github.jayennn.BlockchainVoting.controller.login.LoginHandler;
import io.github.jayennn.BlockchainVoting.view.common.BasePanel;

public class LoginPanel extends BasePanel implements LoginView {
  private JTextField usernameField;
  private JPasswordField passwordField;
  private LoginHandler loginHandler;

  public LoginPanel() {}

  @Override
  public String getUsername() {
    return usernameField.getText();
  }

  @Override
  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void clearFields() {
    usernameField.setText("");
    passwordField.setText("");
  }

  @Override
  public void setLoginHandler(LoginHandler loginHandler) {
    this.loginHandler = loginHandler;
  }

  @Override
  protected void initComponent() {
    setLayout(new BorderLayout());

    // Background image
    ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/login-background.png")));
    JLabel background = new JLabel(bgIcon);
    background.setLayout(new GridBagLayout());
    add(background, BorderLayout.CENTER);

    // === Panel vertikal (logo + form) ===
    JPanel verticalPanel = new JPanel(new GridBagLayout());
    verticalPanel.setOpaque(false); // transparan, biar background terlihat

    GridBagConstraints gbcVertical = new GridBagConstraints();
    gbcVertical.gridx = 0;
    gbcVertical.gridy = 0;
    gbcVertical.insets = new Insets(0, 0, 10, 0);

    // Logo ITK
    ImageIcon itkLogoIcon = new ImageIcon(getClass().getResource("/assets/itk.png"));
    JLabel itkLogoLabel = new JLabel(itkLogoIcon);
    verticalPanel.add(itkLogoLabel, gbcVertical);

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setOpaque(true);
    formPanel.setLayout(new GridBagLayout());
    formPanel.setBackground(new Color(255, 255, 255, 200));
    formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 120, 215, 150), 2),
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
    ));

    formPanel.setPreferredSize(new Dimension(600,480));

    gbcVertical.gridy = 1;
    verticalPanel.add(formPanel, gbcVertical);

    GridBagConstraints gbcFormPanel = new GridBagConstraints();
    gbcFormPanel.gridx = 0;
    gbcFormPanel.gridy = 0;
    gbcFormPanel.anchor = GridBagConstraints.CENTER;
    background.add(verticalPanel, gbcFormPanel);

    addFormComponents(formPanel);
  }

  private void addFormComponents(JPanel formPanel) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 0, 10, 0);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.anchor = GridBagConstraints.CENTER;

    int row = 0;

    JPanel usernamePanel = new JPanel(new GridBagLayout());
    usernamePanel.setOpaque(false);
    JLabel userIcon = new JLabel(new ImageIcon(getClass().getResource("/assets/icons8-user-48.png")));
    userIcon.setPreferredSize(new Dimension(48,40));
    userIcon.setHorizontalAlignment(SwingConstants.CENTER);
    usernameField = new JTextField("Username");
    usernameField.setBackground(Color.WHITE);
    usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    usernameField.setForeground(Color.GRAY);
    usernameField.setPreferredSize(new Dimension(250, 40));

    usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
      @Override
      public void focusGained(java.awt.event.FocusEvent evt) {
        if (usernameField.getText().equals("Username")) {
          usernameField.setText("");
          usernameField.setForeground(Color.BLACK);
        }
      }

      @Override
      public void focusLost(java.awt.event.FocusEvent evt) {
        if (usernameField.getText().isEmpty()) {
          usernameField.setText("Username");
          usernameField.setForeground(Color.GRAY);
        }
      }
    });

    GridBagConstraints gbcUsername = new GridBagConstraints();
    gbcUsername.gridx = 0;
    gbcUsername.insets = new Insets(0, 0, 0, 10);
    usernamePanel.add(userIcon, gbcUsername);

    gbcUsername.gridx = 1;
    gbcUsername.weightx = 1;
    gbcUsername.fill = GridBagConstraints.HORIZONTAL;
    usernamePanel.add(usernameField, gbcUsername);

    gbc.gridy = row++;
    formPanel.add(usernamePanel, gbc);

    JPanel passwordPanel = new JPanel(new GridBagLayout());
    passwordPanel.setOpaque(false);
    JLabel passIcon = new JLabel(new ImageIcon(getClass().getResource("/assets/icons8-password-key-50.png")));
    passIcon.setPreferredSize(new Dimension(48, 48));
    passIcon.setHorizontalAlignment(SwingConstants.CENTER);

    passwordField = new JPasswordField("Password");
    passwordField.setPreferredSize(new Dimension(250, 40));
    passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    passwordField.setForeground(Color.GRAY);
    passwordField.setEchoChar((char) 0);

    passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
      @Override
      public void focusGained(java.awt.event.FocusEvent evt) {
        if (new String(passwordField.getPassword()).equals("Password")) {
          passwordField.setText("");
          passwordField.setForeground(Color.BLACK);
          passwordField.setEchoChar('*');
        }
      }

      @Override
      public void focusLost(java.awt.event.FocusEvent evt) {
        if (new String(passwordField.getPassword()).isEmpty()) {
          passwordField.setEchoChar((char) 0);
          passwordField.setText("Password");
          passwordField.setForeground(Color.GRAY);
        }
      }
    });
    GridBagConstraints gbcPassword = new GridBagConstraints();
    gbcPassword.gridx = 0;
    gbcPassword.insets = new Insets(0, 0, 0, 10);
    gbcPassword.anchor = GridBagConstraints.CENTER;
    gbcPassword.fill = GridBagConstraints.NONE;
    passwordPanel.add(passIcon, gbcPassword);

    gbcPassword.gridx = 1;
    gbcPassword.weightx = 1;
    gbcPassword.fill = GridBagConstraints.HORIZONTAL;
    passwordPanel.add(passwordField, gbcPassword);

    gbc.gridy = row++;
    formPanel.add(passwordPanel, gbc);

    JButton loginButton = new JButton("Login");
    loginButton.setPreferredSize(new Dimension(200, 45));
    loginButton.setBackground(Color.BLACK);
    loginButton.setForeground(Color.WHITE);
    loginButton.setFont(new Font("Arial", Font.BOLD, 16));
    loginButton.setFocusPainted(false);
    loginButton.setBorder(BorderFactory.createEmptyBorder());

    gbc.insets = new Insets(20, 0, 20, 0);
    gbc.gridy = row++;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    formPanel.add(loginButton, gbc);

    loginButton.addActionListener(e -> {
      if (loginHandler != null) {
        loginHandler.handle(getUsername(), getPassword());
      }
    });

    SwingUtilities.invokeLater(() -> {
      JRootPane rootPane = SwingUtilities.getRootPane(formPanel);
      if (rootPane != null) {
        rootPane.setDefaultButton(loginButton);
      }
    });
  }
}