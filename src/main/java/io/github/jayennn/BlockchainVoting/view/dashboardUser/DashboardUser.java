package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardUser extends JPanel {

  private JButton activeButton = null;
  private CardLayout cardLayout;
  private JPanel contentPanel;

  public DashboardUser() {
    initializeComponents();
  }

  public void initializeComponents() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    // Create sidebar
    JPanel sidebar = createSidebar();

    // Create main content area
    cardLayout = new CardLayout();
    contentPanel = new JPanel(cardLayout);
    contentPanel.setBackground(new Color(248, 249, 250));

    // Add content panels
    contentPanel.add(createProfilePanel(), "profile");
    contentPanel.add(createElectionPanel(), "election");
    contentPanel.add(createManageElectionPanel(), "manage");

    // Show profile panel by default
    cardLayout.show(contentPanel, "profile");

    add(sidebar, BorderLayout.WEST);
    add(contentPanel, BorderLayout.CENTER);
  }

  private JPanel createSidebar() {
    JPanel sidebar = new JPanel();
    sidebar.setLayout(new BorderLayout());
    sidebar.setPreferredSize(new java.awt.Dimension(280, 700));
    sidebar.setBackground(Color.WHITE);
    sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(230, 230, 230)));

    // Top section with logo
    JPanel topSection = new JPanel();
    topSection.setLayout(new BorderLayout());
    topSection.setBackground(Color.WHITE);
    topSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));

    // Logo
    try {
      ImageIcon logoIcon = new ImageIcon(getClass().getResource("/assets/itk.png"));
      Image scaledLogo = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
      ImageIcon resizedLogo = new ImageIcon(scaledLogo);
      JLabel logoLabel = new JLabel(resizedLogo);
      logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
      topSection.add(logoLabel, BorderLayout.CENTER);
    } catch (Exception e) {
      // Fallback if logo not found
      JLabel logoLabel = new JLabel("LOGO", SwingConstants.CENTER);
      logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
      logoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      logoLabel.setPreferredSize(new java.awt.Dimension(200, 80));
      topSection.add(logoLabel, BorderLayout.CENTER);
    }

    // Navigation section
    JPanel navSection = new JPanel();
    navSection.setLayout(new GridBagLayout());
    navSection.setBackground(Color.WHITE);
    navSection.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 0, 5, 0);
    gbc.weightx = 1.0;

    // Navigation buttons
    JButton profileBtn = createMenuButton("👤 My Profile", true);
    JButton electionBtn = createMenuButton("🗳️ Election", false);
    JButton manageBtn = createMenuButton("⚙️ Manage Election", false);

    profileBtn.addActionListener(e -> {
      switchActiveButton(profileBtn);
      cardLayout.show(contentPanel, "profile");
    });

    electionBtn.addActionListener(e -> {
      switchActiveButton(electionBtn);
      cardLayout.show(contentPanel, "election");
    });

    manageBtn.addActionListener(e -> {
      switchActiveButton(manageBtn);
      cardLayout.show(contentPanel, "manage");
    });

    // Set initial active button
    activeButton = profileBtn;

    gbc.gridy = 0;
    navSection.add(profileBtn, gbc);
    gbc.gridy = 1;
    navSection.add(electionBtn, gbc);
    gbc.gridy = 2;
    navSection.add(manageBtn, gbc);

    // Bottom section with logout
    JPanel bottomSection = new JPanel();
    bottomSection.setLayout(new BorderLayout());
    bottomSection.setBackground(Color.WHITE);
    bottomSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));

    JButton logoutBtn = createMenuButton("📚 Logout", false);
    logoutBtn.addActionListener(e -> {
      // Handle logout logic here
      System.out.println("Logout clicked");
    });
    bottomSection.add(logoutBtn, BorderLayout.SOUTH);

    sidebar.add(topSection, BorderLayout.NORTH);
    sidebar.add(navSection, BorderLayout.CENTER);
    sidebar.add(bottomSection, BorderLayout.SOUTH);

    return sidebar;
  }

  private JButton createMenuButton(String text, boolean active) {
    JButton button = new JButton(text);
    button.setFocusPainted(false);
    button.setHorizontalAlignment(SwingConstants.LEFT);
    button.setBorderPainted(false);
    button.setFont(new Font("Arial", Font.PLAIN, 14));
    button.setPreferredSize(new java.awt.Dimension(240, 45));
    button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

    if (active) {
      setActiveStyle(button);
    } else {
      setInactiveStyle(button);
    }

    return button;
  }

  private void switchActiveButton(JButton newButton) {
    if (activeButton != null) {
      setInactiveStyle(activeButton);
    }
    activeButton = newButton;
    setActiveStyle(activeButton);
  }

  private void setActiveStyle(JButton button) {
    button.setBackground(new Color(230, 230, 230));
    button.setFont(button.getFont().deriveFont(Font.BOLD));
    button.setOpaque(true);
  }

  private void setInactiveStyle(JButton button) {
    button.setBackground(Color.WHITE);
    button.setFont(button.getFont().deriveFont(Font.PLAIN));
    button.setOpaque(false);
  }

  private JPanel createProfilePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setBackground(new Color(248, 249, 250));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(10, 10, 10, 10);

    // Personal Information Card
    JPanel personalInfoCard = createCard("Personal Information", "Your personal details");
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.4;
    gbc.weighty = 1.0;
    panel.add(personalInfoCard, gbc);

    // Voting History Card
    JPanel votingHistoryCard = createCard("Your Voting History", "A record of all elections you have participated in");
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.6;
    gbc.weighty = 1.0;
    panel.add(votingHistoryCard, gbc);

    return panel;
  }

  private JPanel createCard(String title, String subtitle) {
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());
    card.setBackground(Color.WHITE);
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)));

    // Header
    JPanel header = new JPanel();
    header.setLayout(new BorderLayout());
    header.setBackground(Color.WHITE);

    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setForeground(new Color(51, 51, 51));

    JLabel subtitleLabel = new JLabel(subtitle);
    subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    subtitleLabel.setForeground(new Color(102, 102, 102));

    header.add(titleLabel, BorderLayout.NORTH);
    header.add(subtitleLabel, BorderLayout.CENTER);

    // Content area (placeholder)
    JPanel content = new JPanel();
    content.setBackground(Color.WHITE);
    content.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

    // Add some placeholder content for voting history
    if (title.equals("Your Voting History")) {
      content.setLayout(new BorderLayout());
      JLabel placeholder = new JLabel("Voting history content will be displayed here");
      placeholder.setHorizontalAlignment(SwingConstants.CENTER);
      placeholder.setFont(new Font("Arial", Font.ITALIC, 12));
      placeholder.setForeground(new Color(153, 153, 153));
      content.add(placeholder, BorderLayout.CENTER);
    }

    card.add(header, BorderLayout.NORTH);
    card.add(content, BorderLayout.CENTER);

    return card;
  }

  private JPanel createElectionPanel() {
    return new ElectionPanel();
  }

  private JPanel createManageElectionPanel() {
    return new ElectionHistory();
  }

  private JPanel createContentPanel(String labelText) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBackground(new Color(248, 249, 250));
    panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

    JLabel label = new JLabel(labelText, SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 24));
    label.setForeground(new Color(51, 51, 51));

    panel.add(label, BorderLayout.CENTER);
    return panel;
  }
}