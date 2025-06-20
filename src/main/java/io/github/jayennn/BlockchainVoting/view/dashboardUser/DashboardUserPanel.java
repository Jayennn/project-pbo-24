package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfilePanel;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardUserPanel extends JPanel implements DashboardUserView {

  private CardLayout cardLayout;
  private JPanel contentPanel;
  private JPanel profilePanel;
  private io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.Panel sidebar;

  public DashboardUserPanel() {
    initializeComponents();
  }

  @Override
  public View getSidebarView() {
    return sidebar;
  }

  @Override
  public void switchRightPanel(String panelName) {
    cardLayout.show(contentPanel,panelName);
  }

  @Override
  public CardLayout getCardLayout() {
    return cardLayout;
  }

  @Override
  public JPanel getContenPanel() {
    return contentPanel;
  }

  public void initializeComponents() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    // Create sidebar
    sidebar = new io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.Panel();


    // Create main content area
    cardLayout = new CardLayout();
    contentPanel = new JPanel(cardLayout);
    contentPanel.setBackground(new Color(248, 249, 250));

    // Add content panels
    profilePanel = new ProfilePanel();
    contentPanel.add(profilePanel, "profile");
    contentPanel.add(createElectionPanel(), "election");
    contentPanel.add(createManageElectionPanel(), "manage");

    // Show profile panel by default
    cardLayout.show(contentPanel, "profile");

    add(sidebar, BorderLayout.WEST);
    add(contentPanel, BorderLayout.CENTER);
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