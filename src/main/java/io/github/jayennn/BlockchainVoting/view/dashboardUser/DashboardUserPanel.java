package io.github.jayennn.BlockchainVoting.view.dashboardUser;

import io.github.jayennn.BlockchainVoting.view.dashboardUser.election.Election;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.manage.Manage;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfilePanel;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.profile.ProfileView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SIdebarView;
import io.github.jayennn.BlockchainVoting.view.dashboardUser.sidebar.SidebarPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class DashboardUserPanel extends JPanel implements DashboardUserView {

  private CardLayout cardLayout;
  private JPanel contentPanel;
  private JPanel profilePanel;
  private JPanel electionPanel;
  private JPanel manage;
  private SidebarPanel sidebar;

  public DashboardUserPanel() {
    initializeComponents();
  }

  @Override
  public SIdebarView getSidebarView() {
    return sidebar;
  }

  @Override
  public ProfileView getProfileView() {
    return (ProfileView) profilePanel;
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
    sidebar = new SidebarPanel();


    // Create main content area
    cardLayout = new CardLayout();
    contentPanel = new JPanel(cardLayout);
    contentPanel.setBackground(new Color(248, 249, 250));

    // Add content panels
    profilePanel = new ProfilePanel();
    electionPanel = new Election();
    manage = new Manage();
    contentPanel.add(profilePanel, "profile");
    contentPanel.add(electionPanel, "election");
    contentPanel.add(manage, "manage");

    // Show profile panel by default
    cardLayout.show(contentPanel, "profile");

    add(sidebar, BorderLayout.WEST);
    add(contentPanel, BorderLayout.CENTER);
  }
}